package com.unla.grupo22.tpc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo22.tpc.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/")
public class HomeController {
	
	

	@GetMapping("/") // este metodo responde a una peticion GET sobre la ruta vacia ( / )
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.ROUTE); // redireccion a una nueva ruta del sistema
	}
}
