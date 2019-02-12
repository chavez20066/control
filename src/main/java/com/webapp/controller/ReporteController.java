package com.webapp.controller;
/*
 * gestacion 9 meses promedio intervalo de 276  a 283 dias
 * entran en celo a los 21 dias intervalo 17 a 24 dias
 * la inseminacion se debe dar desde las 12 hasta las 18 horas desde que aparece el celo
 * produccion promedio 10 meses
 * apartir del parto a los dos meses debe darse al celo una vaca
 * */

import java.util.Calendar;
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
public class ReporteController {

	private static final Log LOG = LogFactory.getLog(ReporteController.class);

	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private PartoService partoService;
	
		
	@Secured("ROLE_ADMIN")
	@GetMapping("/reportes/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {
		if (authentication != null) {
			LOG.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}		
		model.addAttribute("titulo", "Reportes");	
		model.addAttribute("classActiveReportes","active");
		return "/reportes/listar";
	}
	
	
	
	@GetMapping(value = "/reportes/ver/{codParto}")
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


