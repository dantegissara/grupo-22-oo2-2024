package com.unla.grupo22.tpc.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo22.tpc.entities.Compra;
import com.unla.grupo22.tpc.entities.DetalleCompra;
import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.entities.User;
import com.unla.grupo22.tpc.services.implementation.CompraService;
import com.unla.grupo22.tpc.services.implementation.ProductoService;
import com.unla.grupo22.tpc.services.implementation.UserService;



@Controller
@RequestMapping("/compras")
public class CompraController {

	private CompraService compraService;
	private ProductoService productoService;
	private UserService userService;
	
	public CompraController(CompraService compraService, ProductoService productoService, UserService userService) {
		super();
		this.compraService = compraService;
		this.productoService = productoService;
		this.userService = userService;
	}
	
	@GetMapping("/nueva")
	public String mostrarFormularioCompra(Model model) {
		model.addAttribute("productos", productoService.getAllProductos());
		return "compras/nueva";
	}
	
	@PostMapping("/realizar")
	public String realizarCompra(
	        @RequestParam("userId") int userId,
	        @RequestParam("productosIds") List<Integer> productosIds,
	        @RequestParam("cantidades") List<Integer> cantidades,
	        Model model) {

	    User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User no encontrado"));

	    List<DetalleCompra> detallesCompra = productosIds.stream().map(productoId -> {
	        Producto producto = productoService.findById(productoId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
	        int cantidad = cantidades.get(productosIds.indexOf(productoId));
	        return new DetalleCompra(null, producto, cantidad);  // Compra se establecerá más adelante
	    }).collect(Collectors.toList());

	    Compra compra = new Compra(user, detallesCompra);
	    detallesCompra.forEach(detalle -> detalle.setCompra(compra));  // Establecer la relación bidireccional

	    compraService.realizarCompra(compra);

	    model.addAttribute("success", "Compra realizada con éxito");
	    return "compras/nueva";
	}



	
}
