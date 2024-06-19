package com.unla.grupo22.tpc.controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.unla.grupo22.tpc.entities.Lote;
import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.entities.StockProducto;
import com.unla.grupo22.tpc.service.IStockProductoService;
import com.unla.grupo22.tpc.services.implementation.ProductoService;
import com.unla.grupo22.tpc.services.implementation.StockService;



@Controller
@RequestMapping("/stock")
public class StockController {

	private StockService stockService;
	private ProductoService productoService;
	private IStockProductoService stockProductoService;
	
	public StockController(StockService stockService, ProductoService productoService,IStockProductoService stockProductoService) {
		super();
		this.stockService = stockService;
		this.productoService= productoService;
		this.stockProductoService = stockProductoService;
	}
	
	@GetMapping("/alta")
    public String mostrarFormularioAlta(Model model) {
        return "stock/altaLote";
    }
	
	@PostMapping("/alta")
	public String altaNuevoLote(
	        @RequestParam("productoId") int productoId,
	        @RequestParam("cantidadRecibida") int cantidadRecibida,
	        @RequestParam("fechaRecepcion") String fechaRecepcion,
	        @RequestParam("proveedor") String proveedor,
	        @RequestParam("precioDeCompra") int precioDeCompra,
	        Model model) {

	    Optional<Producto> productoOpt = productoService.findById(productoId);

	    if (!productoOpt.isPresent()) {
	        model.addAttribute("error", "Producto no encontrado");
	        return "stock/altaLote";
	    }

	    Producto producto = productoOpt.get();
	    
	    LocalDate fechaActual = LocalDate.now();
	    LocalDate fechaIngresada = LocalDate.parse(fechaRecepcion);
	    
	    if (fechaIngresada.isAfter(fechaActual)) {
	        model.addAttribute("error", "La fecha de recepción no puede ser posterior a la fecha actual");
	        return "stock/altaLote";
	    }
	    
	    //guardar lote
	    Lote lote = new Lote(producto, cantidadRecibida, LocalDate.parse(fechaRecepcion), proveedor, precioDeCompra);
	    Lote nuevoLote = stockService.altaNuevoLote(lote);
	    
	    //actualizar o crear StockProducto
	    Optional<StockProducto> stockProductoOpt = stockProductoService.findByProducto(producto);
        if (stockProductoOpt.isPresent()) {
            StockProducto stockProducto = stockProductoOpt.get();
            stockProducto.setCantidad(stockProducto.getCantidad() + cantidadRecibida);
            stockProductoService.actualizar(stockProducto); // Método de servicio para actualizar StockProducto
        } else {
            StockProducto nuevoStockProducto = new StockProducto();
            nuevoStockProducto.setProducto(producto);
            nuevoStockProducto.setCantidad(cantidadRecibida);
            nuevoStockProducto.setMinimoStock(0); 
            stockProductoService.guardar(nuevoStockProducto); // Método de servicio para guardar StockProducto
        }
        
	    model.addAttribute("success", "El alta se realizo correctamente");
	    return "stock/altaLote";
	}
	
}
