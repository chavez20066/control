package com.webapp.controller;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class LoginController {
	
	private static final Log LOG = LogFactory.getLog(LoginController.class);
	

	@GetMapping(value= {"/login", "/"})
	public String login(
			@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required = false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {
		
		LOG.info("METHOD: login() ");
		
		if(principal!=null) {
			flash.addFlashAttribute("info","ya ha iniciado sesión anteriormente");
			return "redirect:/animales";
		}
		if(error!=null) {
			model.addAttribute("error", "error en el login: usuario o contraseña incorrecta.");
		}
		if(logout!=null) {
			model.addAttribute("success", "Ha cerrado sesión con exito");
		}
		
		return "login";
	}
}
