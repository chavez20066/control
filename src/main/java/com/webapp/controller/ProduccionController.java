package com.webapp.controller;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
import com.webapp.entity.Produccion;
import com.webapp.paginator.PageRender;
import com.webapp.service.AnimalService;
import com.webapp.service.ControlService;
import com.webapp.service.EventoService;
import com.webapp.service.ProduccionService;

@Controller
@SessionAttributes("produccion")
public class ProduccionController {

	private static final Log LOG = LogFactory.getLog(ProduccionController.class);

	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private ProduccionService produccionService;
	
	@Autowired
	private EventoService eventoService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/producciones/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {
		if (authentication != null) {
			LOG.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}
		Pageable pageRequest = PageRequest.of(page, 4);
		//Page<Animal> animales = animalService.findAll(pageRequest);		
		Page<Produccion> producciones=produccionService.findAll(pageRequest);
		
		PageRender<Produccion> pageRender = new PageRender<Produccion>("/producciones/listar", producciones); // ruta del paginador
		model.addAttribute("titulo", "Listado de producciones");
		model.addAttribute("producciones", producciones);
		model.addAttribute("page", pageRender);
		model.addAttribute("classActiveProducciones","active");
		return "/producciones/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/producciones/form")
	public String crear(Map<String, Object> model) {
		
		LOG.info("crear():");

		List<Animal> animales = animalService.finAllOrderByNombre();	
		
		Produccion produccion=new Produccion();
		model.put("produccion", produccion);
		model.put("animales", animales);	
		model.put("titulo", "Registrar Produccion");
		return "/producciones/form";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/producciones/form", method = RequestMethod.POST)
	public String guardar(@Valid Produccion produccion, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		LOG.info("guardar(): " + produccion.toString());
		//LOG.info("padrillo(): " + animal.getPadrillo());
		
				
		Animal animal = animalService.findByCodAnimal(produccion.getAnimal().getCodAnimal());

			
		LOG.info("guardar() animal: " + animal.toString()); 
		
		
		produccion.setAnimal(animal);	

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de producciones");
			return "/producciones/form";
		}
				
		String mensajeFlash = (produccion.getCodProduccion() != null) ? "Produccion editada con éxito!"	: "Produccion creada con éxito!";

		LOG.info("guardar (): " + produccion.toString());

		produccionService.save(produccion);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/producciones/listar";
	}
	
	@RequestMapping(value = "/producciones/form/{codProduccion}")
	public String editar(@PathVariable(value = "codProduccion") Long codProduccion, Map<String, Object> model, RedirectAttributes flash) {

		Produccion produccion = null;

		if (codProduccion > 0) {
			produccion = produccionService.findByCodProduccion(codProduccion);			
			
			if (produccion == null) {
				flash.addFlashAttribute("error", "El código de produccion no existe en la BBDD!");
				return "redirect:/producciones/listar";
			}
			else {
				LOG.info("EDITAR() codAnimal: " + produccion.getAnimal().getCodAnimal());				
				Animal animal = animalService.findByCodAnimal(produccion.getAnimal().getCodAnimal());

				produccion.setAnimal(animal);
			}
		} else {
			flash.addFlashAttribute("error", "El código de producciones no puede ser cero!");
			return "redirect:/producciones/listar";
		}
		
		List<Animal> animales = animalService.finAllOrderByNombre();
		
				
		model.put("produccion", produccion);
		model.put("animales", animales);		
		model.put("titulo", "Editar Produccion");	
		
		
		return "/producciones/form";
	}
	
	
	@RequestMapping(value = "/producciones/eliminar/{codProduccion}")
	public String eliminar(@PathVariable(value = "codProduccion") Long codProduccion, RedirectAttributes flash) {

		if (codProduccion > 0) {
			//Control control =  controlService.findByCodControl(codControl);

			produccionService.delete(codProduccion);
			flash.addFlashAttribute("success", "Produccion eliminada con éxito!");
	
		}
		return "redirect:/producciones/listar";
	}
	
	@GetMapping(value = "/producciones/ver/{codProduccion}")
	public String ver(@PathVariable(value = "codProduccion") Long codProduccion, Map<String, Object> model,
			RedirectAttributes flash) {

				
		Produccion produccion = produccionService.findByCodProduccion(codProduccion);	
		//Animal animal = animalService.findByCodAnimal(produccion.getAnimal().getCodAnimal());

		if (produccion == null) {
			flash.addFlashAttribute("error", "la produccion animal no existe en la base de datos");
			return "redirect:/producciones/listar";
		}
				

		model.put("produccion", produccion);
		model.put("titulo", "Detalle Produccion: " + produccion.getAnimal().getNombre());
		return "/producciones/ver";
	}
	
}


