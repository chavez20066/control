package com.webapp.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webapp.entity.Animal;
import com.webapp.entity.Control;
import com.webapp.entity.Evento;
import com.webapp.paginator.PageRender;
import com.webapp.service.AnimalService;
import com.webapp.service.ControlService;
import com.webapp.service.EventoService;

@Controller
@SessionAttributes("control")
public class ControlController {

	private static final Log LOG = LogFactory.getLog(ControlController.class);

	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private ControlService controlService;
	
	@Autowired
	private EventoService eventoService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/controles/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {
		if (authentication != null) {
			LOG.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}
		Pageable pageRequest = PageRequest.of(page, 4);
		//Page<Animal> animales = animalService.findAll(pageRequest);		
		Page<Control> controles=controlService.findAll(pageRequest);
		
		PageRender<Control> pageRender = new PageRender<Control>("/controles/listar", controles); // ruta del paginador
		model.addAttribute("titulo", "Listado de controles");
		model.addAttribute("controles", controles);
		model.addAttribute("page", pageRender);
		model.addAttribute("classActiveControles","active");
		return "/controles/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/controles/form")
	public String crear(Map<String, Object> model) {
		
		LOG.info("crear():");

		List<Animal> animales = animalService.finAllOrderByNombre();
				
		List<Evento> eventos = eventoService.findEventoAllOrderByDescripcion();
		
		Control control =new Control();
		model.put("control", control);
		model.put("animales", animales);	
		model.put("eventos", eventos);	
		model.put("titulo", "Registrar Control");
		model.put("classActiveControles","active");
		return "/controles/form";
	}
	
	/*@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/controles/form/{codAnimal}")
	public String registrar(@PathVariable(value = "codAnimal") Long codAnimal, Map<String, Object> model) {
		
		LOG.info("crear():");

		List<Animal> animales = animalService.finAllOrderByNombre();
		
		Animal animal = animalService.findByCodAnimal(codAnimal);
				
		List<Evento> eventos = eventoService.findEventoAllOrderByDescripcion();
		
		Control control =new Control();
		control.setAnimal(animal);
		
		model.put("control", control);
		model.put("animales", animales);	
		model.put("eventos", eventos);	
		model.put("titulo", "Registrar Control");
		model.put("classActiveControles","active");
		return "/controles/form";
	}*/
	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/controles/form", method = RequestMethod.POST)
	public String guardar(@Valid Control control, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		LOG.info("guardar(): " + control.toString());
		//LOG.info("padrillo(): " + animal.getPadrillo());
		
				
		Evento evento=eventoService.findByCodEvento(control.getEvento().getCodEvento());
		Animal animal = animalService.findByCodAnimal(control.getAnimal().getCodAnimal());

		LOG.info("guardar() evento: " + evento.toString());
		
		LOG.info("guardar() animal: " + animal.toString()); 
		
		
		control.setAnimal(animal);
		control.setEvento(evento);

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Controles");
			return "/controles/form";
		}
				
		String mensajeFlash = (control.getCodControl() != null) ? "Control editado con éxito!"	: "Control creado con éxito!";

		LOG.info("guardar (): " + control.toString());

		controlService.save(control);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/controles/listar";
	}
	
	@RequestMapping(value = "/controles/form/{codControl}")
	public String editar(@PathVariable(value = "codControl") Long codControl, Map<String, Object> model, RedirectAttributes flash) {

		Control control = null;

		if (codControl > 0) {
			control = controlService.findByCodControl(codControl);			
			
			if (control == null) {
				flash.addFlashAttribute("error", "El código del control no existe en la BBDD!");
				return "redirect:/controles/listar";
			}
			else {
				LOG.info("EDITAR() codAnimal: " + control.getAnimal().getCodAnimal());				
				Evento evento=eventoService.findByCodEvento(control.getEvento().getCodEvento());
				Animal animal = animalService.findByCodAnimal(control.getAnimal().getCodAnimal());
				
				
				LOG.info("EDITAR() evento: " + evento.toString());

				control.setAnimal(animal);
				control.setEvento(evento);
			}
		} else {
			flash.addFlashAttribute("error", "El código del control no puede ser cero!");
			return "redirect:/controles/listar";
		}
		
		List<Animal> animales = animalService.finAllOrderByNombre();
		
		List<Evento> eventos = eventoService.findEventoAllOrderByDescripcion();
		
		model.put("control", control);
		model.put("animales", animales);	
		model.put("eventos", eventos);	
		model.put("titulo", "Editar Control");
		model.put("classActiveControles","active");
		
		
		return "/controles/form";
	}
	
	
	@RequestMapping(value = "/controles/eliminar/{codControl}")
	public String eliminar(@PathVariable(value = "codControl") Long codControl, RedirectAttributes flash) {

		if (codControl> 0) {
			//Control control =  controlService.findByCodControl(codControl);

			controlService.delete(codControl);
			flash.addFlashAttribute("success", "Animal eliminado con éxito!");
	
		}
		return "redirect:/controles/listar";
	}
	
	@GetMapping(value = "/controles/ver/{codControl}")
	public String ver(@PathVariable(value = "codControl") Long codControl, Map<String, Object> model,
			RedirectAttributes flash) {

				
		Control control = controlService.findByCodControl(codControl);	
		//Animal animal = animalService.findByCodAnimal(produccion.getAnimal().getCodAnimal());

		if (control == null) {
			flash.addFlashAttribute("error", "El control no existe en la base de datos");
			return "redirect:/controles/listar";
		}
				

		model.put("control", control);
		model.put("titulo", "Detalle Control: " + control.getAnimal().getNombre());
		model.put("classActiveControles","active");
		return "/controles/ver";
	}
}


