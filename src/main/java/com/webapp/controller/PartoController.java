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
import com.webapp.entity.Parto;
import com.webapp.paginator.PageRender;
import com.webapp.service.AnimalService;
import com.webapp.service.PartoService;

@Controller
@SessionAttributes("parto")
public class PartoController {

	private static final Log LOG = LogFactory.getLog(PartoController.class);

	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private PartoService partoService;
	
		
	@Secured("ROLE_ADMIN")
	@GetMapping("/partos/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {
		if (authentication != null) {
			LOG.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}
		Pageable pageRequest = PageRequest.of(page, 4);
		//Page<Animal> animales = animalService.findAll(pageRequest);		
		Page<Parto> partos=partoService.findAll(pageRequest);
		
		PageRender<Parto> pageRender = new PageRender<Parto>("/partos/listar", partos); // ruta del paginador
		model.addAttribute("titulo", "Listado de partos");
		model.addAttribute("partos", partos);
		model.addAttribute("page", pageRender);
		model.addAttribute("classActivePartos","active");
		return "/partos/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/partos/form")
	public String crear(Map<String, Object> model) {
		
		LOG.info("crear():");

		List<Animal> animales = animalService.finAllOrderByNombre();	
		
		Parto parto=new Parto();
		parto.setNroCrias(1);
		model.put("parto", parto);
		model.put("animales", animales);	
		model.put("titulo", "Registrar Parto");
		model.put("classActivePartos","active");
		model.put("nro","1");
		return "/partos/form";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/partos/form", method = RequestMethod.POST)
	public String guardar(@Valid Parto parto, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {

		LOG.info("guardar(): " + parto.toString());
		//LOG.info("padrillo(): " + animal.getPadrillo());
		
				
		Animal animal = animalService.findByCodAnimal(parto.getAnimal().getCodAnimal());

			
		LOG.info("guardar() animal: " + animal.toString()); 
		
		
		parto.setAnimal(animal);	

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de partos");
			return "/partos/form";
		}
				
		String mensajeFlash = (parto.getCodParto() != null) ? "Parto editada con éxito!"	: "Parto creado con éxito!";

		LOG.info("guardar (): " + parto.toString());

		partoService.save(parto);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/partos/listar";
	}
	
	@RequestMapping(value = "/partos/form/{codParto}")
	public String editar(@PathVariable(value = "codParto") Long codParto, Map<String, Object> model, RedirectAttributes flash) {

		Parto parto = null;

		if (codParto > 0) {
			parto = partoService.findByCodParto(codParto);			
			
			if (parto == null) {
				flash.addFlashAttribute("error", "El código de parto no existe en la BBDD!");
				return "redirect:/partos/listar";
			}
			else {
				LOG.info("EDITAR() codAnimal: " + parto.getAnimal().getCodAnimal());				
				Animal animal = animalService.findByCodAnimal(parto.getAnimal().getCodAnimal());

				parto.setAnimal(animal);
			}
		} else {
			flash.addFlashAttribute("error", "El código de parto no puede ser cero!");
			return "redirect:/partos/listar";
		}
		
		List<Animal> animales = animalService.finAllOrderByNombre();
				
		model.put("parto", parto);
		model.put("animales", animales);		
		model.put("titulo", "Editar Parto");			
		return "/partos/form";
	}
	
	
	@RequestMapping(value = "/partos/eliminar/{codParto}")
	public String eliminar(@PathVariable(value = "codParto") Long codParto, RedirectAttributes flash) {

		if (codParto > 0) {
			//Control control =  controlService.findByCodControl(codControl);

			partoService.delete(codParto);
			flash.addFlashAttribute("success", "Parto eliminado con éxito!");
	
		}
		return "redirect:/partos/listar";
	}
	
	@GetMapping(value = "/partos/ver/{codParto}")
	public String ver(@PathVariable(value = "codParto") Long codParto, Map<String, Object> model,
			RedirectAttributes flash) {

				
		Parto parto = partoService.findByCodParto(codParto);
		
		//Animal animal = animalService.findByCodAnimal(produccion.getAnimal().getCodAnimal());

		if (parto == null) {
			flash.addFlashAttribute("error", "la parto no existe en la base de datos");
			return "redirect:/partos/listar";
		}
				

		model.put("parto", parto);
		model.put("titulo", "Detalle Parto: " + parto.getAnimal().getNombre());
		return "/partos/ver";
	}
	
}


