package com.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.webapp.entity.Usuario;
import com.webapp.repository.UsuarioRepository;
import com.webapp.service.AnimalService;
import com.webapp.service.ClienteServiceImpl;
import com.webapp.entity.Animal;
import com.webapp.entity.Cliente;
import com.webapp.paginator.PageRender;

@Controller 
public class AnimalController {
	
	//protected final Log logger = LogFactory.getLog(this.getClass());
	private static final Log LOG = LogFactory.getLog(AnimalController.class);
	
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AnimalService animalService;
	
	@RequestMapping(value= {"/home"})
	public ModelAndView home(Authentication authentication) {	
		Usuario usuario=usuarioRepository.findByUsername(authentication.getName());		
		ModelAndView mav=new ModelAndView("home");		
		
		mav.addObject("usuario","Sr. "+ usuario.getUsername());		
		mav.addObject("classActiveHome","active");
		return mav;
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/animales")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication,
			HttpServletRequest request) {
		if(authentication != null) {
			LOG.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}
		
		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Animal> animales = animalService.findAll(pageRequest);
		
		PageRender<Animal> pageRender = new PageRender<Animal>("/animales", animales);
		model.addAttribute("titulo", "Listado de animales");
		model.addAttribute("animales", animales);
		model.addAttribute("page", pageRender);
		//model.addAttribute("classActiveHome","active");
		return "listar";
	}
	
	
	
	
}
