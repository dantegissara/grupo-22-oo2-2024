package com.unla.grupo22.tpc.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import com.unla.grupo22.tpc.helpers.ViewRouteHelper;
import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.services.implementation.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired 
	private ProductoService productoService;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("producto") Producto producto) {
		productoService.createProducto(modelMapper.map(producto, Producto.class));
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_INDEX);
		mAV.addObject("productos", productoService.getAllProductos()); // ok
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_NEW);
		mAV.addObject("producto", new Producto());
		return mAV;
	}
	
	@GetMapping("/{id}") // se recibe la variable id
	public ModelAndView get(@PathVariable("id") int id) throws Exception { // el valor de la variable id viene cargado en el path ( la url de la pagina )
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_UPDATE); // levanta a PRODUCTO_UPDATE ( ver comentario en ViewRouteHelper )
		Producto producto = modelMapper.map(productoService.findById(id).get(), Producto.class);
		mAV.addObject("producto", producto);
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("producto") Producto producto) throws Exception { 
		Producto productoToUpdate = modelMapper.map(productoService.findById(producto.getId()).get(), Producto.class);
		if(productoToUpdate != null ) {
			productoToUpdate.setNombre(producto.getNombre());
			productoService.updateProducto(productoToUpdate);
		}
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	

	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		productoService.deleteProducto(id);
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}

}
