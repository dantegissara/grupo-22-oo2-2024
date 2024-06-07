package com.unla.grupo22.tpc.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo22.tpc.helpers.ViewRouteHelper;
import com.unla.grupo22.tpc.service.IProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private IProductoService productoService;
	
	public HomeController(IProductoService productoService) {
		this.productoService = productoService;
	}

	//GET Example: SERVER/index
	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX); // Genera un objeto y se le envia una ruta ( ViewRouteHelper.INDEX ) de ubicacion de la VISTA que vamos a mostrar al usuario
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Esta linea devuelve el usuario que esta logeado, al tenerlo podemos inyectarlo en la VISTA
		modelAndView.addObject("username", user.getUsername()); // nombre clave y objeto a mostrar
		modelAndView.addObject("productos", productoService.getAllProductos()); // nombre clave y objeto a mostra ( productos del sistema )
		return modelAndView;
	}

	@GetMapping("/") // este metodo responde a una peticion GET sobre la ruta vacia ( / )
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.ROUTE); // redireccion a una nueva ruta del sistema
	}
}
