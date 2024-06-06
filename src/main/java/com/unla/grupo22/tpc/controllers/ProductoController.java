package com.unla.grupo22.tpc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


import com.unla.grupo22.tpc.helpers.ViewRouteHelper;
import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.services.implementation.ProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	//@Autowired ?
	private ProductoService productoService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("producto") Producto producto) {
		productoService.createProducto(modelMapper.map(producto, Producto.class));
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	

}
