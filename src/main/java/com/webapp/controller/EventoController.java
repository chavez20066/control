package com.webapp.controller;

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

import com.webapp.entity.Evento;
import com.webapp.paginator.PageRender;
import com.webapp.service.EventoService;

@Controller
@SessionAttributes("evento")
public class EventoController {
	
	private static final Log LOG = LogFactory.getLog(EventoController.class);

	@Autowired
	private EventoService eventoService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/eventos/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {
		if (authentication != null) {
			LOG.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}

		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Evento> eventos = eventoService.findAll(pageRequest);

		PageRender<Evento> pageRender = new PageRender<Evento>("/eventos/listar", eventos); // ruta del paginador
		model.addAttribute("titulo", "Listado de Eventos");
		model.addAttribute("eventos", eventos);
		model.addAttribute("page", pageRender);
		model.addAttribute("classActiveEventos","active");
		return "/eventos/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eventos/form")
	public String crear(Map<String, Object> model) {
		
		Evento evento = new Evento();
		
		model.put("evento", evento);		
		model.put("titulo", "Registrar Evento");
		return "/eventos/form";
	}
	
	@GetMapping(value = "/eventos/ver/{codEvento}")
	public String ver(@PathVariable(value = "codEvento") Long codEvento, Map<String, Object> model,
			RedirectAttributes flash) {

		// Cliente cliente = clienteService.fetchByIdWithFacturas(id);
		
		Evento evento= eventoService.findByCodEvento(codEvento);

		if (evento == null) {
			flash.addFlashAttribute("error", "El evento no existe en la base de datos");
			return "redirect:/eventos/listar";
		}
		
		model.put("evento", evento);
		model.put("titulo", "Detalle del Evento: " + evento.getDescripcion());
		model.put("classActiveEventos","active");
		return "/eventos/ver";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/eventos/form", method = RequestMethod.POST)
	public String guardar(@Valid Evento evento, BindingResult result, Model model,
			 RedirectAttributes flash, SessionStatus status) {

		LOG.info("guardarEvento(): " + evento.toString());
		//LOG.info("padrillo(): " + animal.getPadrillo());
				
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Eventos");
			return "/eventos/form";
		}
				

		String mensajeFlash = (evento.getCodEvento() != null) ? "Evento editado con éxito!"	: "Evento creado con éxito!";

		eventoService.save(evento);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/eventos/listar";
	}
	
	@RequestMapping(value = "/eventos/form/{codEvento}")
	public String editar(@PathVariable(value = "codEvento") Long codEvento, Map<String, Object> model, RedirectAttributes flash) {

		Evento evento = null;

		if (codEvento > 0) {
			evento = eventoService.findByCodEvento(codEvento);			
			
			if (evento == null) {
				flash.addFlashAttribute("error", "El código del evento no existe en la BBDD!");
				return "redirect:/eventos/listar";
			}
			
		} else {
			flash.addFlashAttribute("error", "El código del evento no puede ser cero!");
			return "redirect:/eventos/listar";
		}
						
		
		LOG.info("EDITAR (): " + evento.toString());
		
		model.put("evento", evento);		
		model.put("titulo", "Editar Evento");
		return "/eventos/form";
	}
	
	@RequestMapping(value = "/eventos/eliminar/{codEvento}")
	public String eliminar(@PathVariable(value = "codEvento") Long codEvento, RedirectAttributes flash) {

		if (codEvento> 0) {
			//Evento evento =  eventoService.findByCodEvento(codEvento);

			eventoService.delete(codEvento);
			flash.addFlashAttribute("success", "Evento eliminado con éxito!");

		}
		return "redirect:/eventos/listar";
	}


}
