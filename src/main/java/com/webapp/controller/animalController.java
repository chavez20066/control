package com.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import com.webapp.entity.Usuario;
import com.webapp.repository.UsuarioRepository;
import com.webapp.entity.Cliente;
import com.webapp.paginator.PageRender;

@Controller 
public class animalController {
	
	//protected final Log logger = LogFactory.getLog(this.getClass());
	private static final Log LOG = LogFactory.getLog(animalController.class);
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;

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
		
		//PageRender<Cliente> pageRender = new PageRender<Cliente>("/listar", clientes);
		model.addAttribute("titulo", "Listado de animales");
		//model.addAttribute("clientes", clientes);
		//model.addAttribute("page", pageRender);
		//model.addAttribute("classActiveHome","active");
		return "listar";
	}
	
}
