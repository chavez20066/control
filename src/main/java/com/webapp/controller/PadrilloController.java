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

import com.webapp.entity.Padrillo;
import com.webapp.paginator.PageRender;
import com.webapp.service.PadrilloService;

@Controller
@SessionAttributes("padrillo")
public class PadrilloController {
	
	private static final Log LOG = LogFactory.getLog(PadrilloController.class);

	@Autowired
	private PadrilloService padrilloService;
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/padrillos/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {
		if (authentication != null) {
			LOG.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}

		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Padrillo> padrillos = padrilloService.findAll(pageRequest);

		PageRender<Padrillo> pageRender = new PageRender<Padrillo>("/padrillos/listar", padrillos); // ruta del paginador
		model.addAttribute("titulo", "Listado de padrillos");
		model.addAttribute("padrillos", padrillos);
		model.addAttribute("page", pageRender);
		model.addAttribute("classActivePadrillos","active");
		return "/padrillos/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/padrillos/form")
	public String crear(Map<String, Object> model) {
		
		Padrillo padrillo = new Padrillo();
		
		model.put("padrillo", padrillo);		
		model.put("titulo", "Registrar Padrillo");
		return "/padrillos/form";
	}
	
	@GetMapping(value = "/padrillos/ver/{codPadrillo}")
	public String ver(@PathVariable(value = "codPadrillo") Long codPadrillo, Map<String, Object> model,
			RedirectAttributes flash) {

		// Cliente cliente = clienteService.fetchByIdWithFacturas(id);
		
		Padrillo padrillo= padrilloService.findBycodPadrillo(codPadrillo);

		if (padrillo == null) {
			flash.addFlashAttribute("error", "El padrillo no existe en la base de datos");
			return "redirect:/padrillos/listar";
		}
		
		model.put("padrillo", padrillo);
		model.put("titulo", "Detalle del Padrillo: " + padrillo.getNombre());
		return "/padrillos/ver";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/padrillos/form", method = RequestMethod.POST)
	public String guardar(@Valid Padrillo padrillo, BindingResult result, Model model,
			 RedirectAttributes flash, SessionStatus status) {

		LOG.info("guardarPadrillo(): " + padrillo.toString());
		//LOG.info("padrillo(): " + animal.getPadrillo());
				
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Padrillos");
			return "/padrillos/form";
		}
				

		String mensajeFlash = (padrillo.getCodPadrillo() != null) ? "Padrillo editado con éxito!"	: "Padrillo creado con éxito!";

		padrilloService.save(padrillo);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/padrillos/listar";
	}
	
	@RequestMapping(value = "/padrillos/form/{codPadrillo}")
	public String editar(@PathVariable(value = "codPadrillo") Long codPadrillo, Map<String, Object> model, RedirectAttributes flash) {

		Padrillo padrillo = null;

		if (codPadrillo > 0) {
			padrillo = padrilloService.findBycodPadrillo(codPadrillo);			
			
			if (padrillo == null) {
				flash.addFlashAttribute("error", "El código del padrillo no existe en la BBDD!");
				return "redirect:/padrillos/listar";
			}
			
		} else {
			flash.addFlashAttribute("error", "El código del padrillo no puede ser cero!");
			return "redirect:/animales/listar";
		}
						
		
		LOG.info("EDITAR (): " + padrillo.toString());
		
		model.put("padrillo", padrillo);		
		model.put("titulo", "Editar Padrillo");
		return "/padrillos/form";
	}
	
	@RequestMapping(value = "/padrillos/eliminar/{codPadrillo}")
	public String eliminar(@PathVariable(value = "codPadrillo") Long codPadrillo, RedirectAttributes flash) {

		if (codPadrillo> 0) {
			//Padrillo padrillo =  padrilloService.findBycodPadrillo(codPadrillo);

			padrilloService.delete(codPadrillo);
			flash.addFlashAttribute("success", "Padrillo eliminado con éxito!");

		}
		return "redirect:/padrillos/listar";
	}


}
