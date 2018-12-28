package com.webapp.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.webapp.entity.Usuario;
import com.webapp.repository.UsuarioRepository;
import com.webapp.service.AnimalService;
import com.webapp.service.IUploadFileService;
import com.webapp.service.PadrilloService;
import com.webapp.entity.Animal;
import com.webapp.entity.Padrillo;
import com.webapp.paginator.PageRender;

@Controller
@SessionAttributes("animal")
public class AnimalController {

	// protected final Log logger = LogFactory.getLog(this.getClass());
	private static final Log LOG = LogFactory.getLog(AnimalController.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AnimalService animalService;

	@Autowired
	private PadrilloService padrilloService;

	@Autowired
	private IUploadFileService uploadFileService;

	@RequestMapping(value = { "/home" })
	public ModelAndView home(Authentication authentication) {
		Usuario usuario = usuarioRepository.findByUsername(authentication.getName());
		ModelAndView mav = new ModelAndView("home");

		mav.addObject("usuario", "Sr. " + usuario.getUsername());
		mav.addObject("classActiveHome", "active");
		return mav;
	}

	@GetMapping(value = "/animales/ver/{codAnimal}")
	public String ver(@PathVariable(value = "codAnimal") Long codAnimal, Map<String, Object> model,
			RedirectAttributes flash) {

		// Cliente cliente = clienteService.fetchByIdWithFacturas(id);
		Animal animal = animalService.findByCodAnimal(codAnimal);

		if (animal == null) {
			flash.addFlashAttribute("error", "El animal no existe en la base de datos");
			return "redirect:/animales/listar";
		}

		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(new SimpleDateFormat("dd/MM/yyyy").format(animal.getFechaNacimiento()),
				fmt);
		LocalDate ahora = LocalDate.now();

		Period periodo = Period.between(fechaNac, ahora);
		System.out.printf("Tu edad es: %s años, %s meses y %s días", periodo.getYears(), periodo.getMonths(),
				periodo.getDays());
		String Edad = periodo.getYears() + " años, " + periodo.getMonths() + " meses, " + periodo.getDays() + " dias";

		model.put("edad", Edad);
		model.put("animal", animal);
		model.put("titulo", "Detalle del Animal: " + animal.getNombre());
		return "/animales/ver";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/animales/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication, HttpServletRequest request) {
		if (authentication != null) {
			LOG.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}

		Pageable pageRequest = PageRequest.of(page, 4);

		Page<Animal> animales = animalService.findAll(pageRequest);

		PageRender<Animal> pageRender = new PageRender<Animal>("listar", animales); // ruta del paginador
		model.addAttribute("titulo", "Listado de animales");
		model.addAttribute("animales", animales);
		model.addAttribute("page", pageRender);
		model.addAttribute("classActiveAnimales","active");
		return "/animales/listar";
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/animales/buscar")
	public String buscar(@RequestParam(name = "txt_animal") String term, Model model,
			Authentication authentication, HttpServletRequest request) {
		if (authentication != null) {
			LOG.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}

		//Page<Animal> animales = animalService.findAll(pageRequest);

		List<Animal> animales=animalService.findByNombreAndCodAnimal(term);
		
		//PageRender<Animal> pageRender = new PageRender<Animal>("listar", (Page<Animal>) animales); // ruta del paginador
		//PageRender<Animal> pageRender=new Pa
		model.addAttribute("titulo", "Resultado de Busqueda: " + term);
		model.addAttribute("animales", animales);
		model.addAttribute("page", null);
		model.addAttribute("classActiveAnimales","active");
		return "/animales/listar";
	}

	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/animales/form")
	public String crear(Map<String, Object> model) {

		List<Animal> animales = animalService.finAllOrderByNombre();

		List<Padrillo> padrillos = padrilloService.findPadrilloAllOrderByNombre();

		Animal animal = new Animal();
		model.put("animal", animal);
		model.put("animales", animales);
		model.put("padrillos", padrillos);
		model.put("titulo", "Registrar Animal");
		return "/animales/form";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/animales/form", method = RequestMethod.POST)
	public String guardar(@Valid Animal animal, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {

		LOG.info("guardarAnimal(): " + animal.toString());
		//LOG.info("padrillo(): " + animal.getPadrillo());
		
				
		Padrillo padrillo = padrilloService.findBycodPadrillo(animal.getPadrillo().getCodPadrillo());
		Animal madre = animalService.findByCodAnimal(animal.getMadre().getCodAnimal());

		LOG.info("EDITAR() madre: " + madre.toString());
		
		LOG.info("EDITAR() padrillo: " + padrillo.toString()); 
		
		
		animal.setPadrillo(padrillo);
		animal.setMadre(madre);

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Animales");
			return "/animales/form";
		}

		LOG.info("foto (): " + foto.isEmpty());
		if (!foto.isEmpty()) {

			if (animal.getCodAnimal() != null && animal.getCodAnimal() > 0 && animal.getFoto() != null
					&& animal.getFoto().length() > 0) {

				uploadFileService.delete(animal.getFoto());
			}

			String uniqueFilename = null;
			try {
				uniqueFilename = uploadFileService.copy(foto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");

			animal.setFoto(uniqueFilename);

		}

		String mensajeFlash = (animal.getCodAnimal() != null) ? "Animal editado con éxito!"	: "Animal creado con éxito!";

		LOG.info("PRUEBA (): " + animal.toString());

		animalService.save(animal);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}
	
	@RequestMapping(value = "/animales/form/{codAnimal}")
	public String editar(@PathVariable(value = "codAnimal") Long codAnimal, Map<String, Object> model, RedirectAttributes flash) {

		Animal animal = null;

		if (codAnimal > 0) {
			animal = animalService.findByCodAnimal(codAnimal);			
			
			if (animal == null) {
				flash.addFlashAttribute("error", "El código del animal no existe en la BBDD!");
				return "redirect:/animales/listar";
			}
			else {
				LOG.info("EDITAR() codMadre: " + animal.getMadre().getCodAnimal());
				Padrillo padrillo = padrilloService.findBycodPadrillo(animal.getPadrillo().getCodPadrillo());
				Animal madre = animalService.findByCodAnimal(animal.getMadre().getCodAnimal());
				
				LOG.info("EDITAR() madre: " + madre.toString());

				animal.setPadrillo(padrillo);
				animal.setMadre(madre);
			}
		} else {
			flash.addFlashAttribute("error", "El código del cliente no puede ser cero!");
			return "redirect:/animales/listar";
		}
		
		List<Animal> animales = animalService.finAllOrderByNombre();

		List<Padrillo> padrillos = padrilloService.findPadrilloAllOrderByNombre();
		
		
		
		LOG.info("EDITAR (): " + animal.toString());
		
		model.put("animal", animal);
		model.put("animales", animales);
		model.put("padrillos", padrillos);
		model.put("titulo", "Editar Animal");
		return "/animales/form";
	}
	
	@RequestMapping(value = "/animales/eliminar/{codAnimal}")
	public String eliminar(@PathVariable(value = "codAnimal") Long codAnimal, RedirectAttributes flash) {

		if (codAnimal> 0) {
			Animal animal =  animalService.findByCodAnimal(codAnimal);

			animalService.delete(codAnimal);
			flash.addFlashAttribute("success", "Animal eliminado con éxito!");

			if (uploadFileService.delete(animal.getFoto())) {
				flash.addFlashAttribute("info", "Foto " + animal.getFoto() + " eliminada con exito!");
			}

		}
		return "redirect:/animales/listar";
	}

}
