package com.unla.grupo22.tpc.controllers;




import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import com.unla.grupo22.tpc.helpers.ViewRouteHelper;
import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.entities.StockProducto;
import com.unla.grupo22.tpc.services.implementation.ProductoService;



@Controller
@RequestMapping("/producto")
public class ProductoController {
	
	@Autowired 
	private ProductoService productoService;
	
	private ModelMapper modelMapper = new ModelMapper();

	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("producto") Producto producto) {
		
        if (producto.getStockProducto() != null) {
            producto.getStockProducto().setProducto(producto);
        }
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
        Producto producto = new Producto();
        producto.setStockProducto(new StockProducto()); // Inicializar StockProducto
        mAV.addObject("producto", producto);
        return mAV;
    }
	
    @GetMapping("/{id}") // se recibe la variable id
    public ModelAndView get(@PathVariable("id") int id) throws Exception { // el valor de la variable id viene cargado en el path ( la url de la pagina )
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_UPDATE); // levanta a PRODUCTO_UPDATE ( ver comentario en ViewRouteHelper )
        Producto producto = productoService.findById(id).orElse(null);
        if (producto != null && producto.getStockProducto() == null) {
            producto.setStockProducto(new StockProducto()); // Inicializar StockProducto si no existe
        }
        mAV.addObject("producto", producto);
        return mAV;
    }
    
    @PostMapping("/update")
    public RedirectView update(@ModelAttribute("producto") Producto producto) throws Exception { 
        Producto productoToUpdate = productoService.findById(producto.getId()).orElse(null);
        if (productoToUpdate != null) {
            productoToUpdate.setCodigo(producto.getCodigo());
            productoToUpdate.setNombre(producto.getNombre());
            productoToUpdate.setDescripcion(producto.getDescripcion());
            productoToUpdate.setCosto(producto.getCosto());
            productoToUpdate.setPrecioDeVenta(producto.getPrecioDeVenta());

            StockProducto stockProducto = productoToUpdate.getStockProducto();
            if (stockProducto != null) {
                stockProducto.setCantidad(producto.getStockProducto().getCantidad());
                stockProducto.setMinimoStock(producto.getStockProducto().getMinimoStock());
            } else {
                StockProducto newStockProducto = new StockProducto();
                newStockProducto.setCantidad(producto.getStockProducto().getCantidad());
                newStockProducto.setMinimoStock(producto.getStockProducto().getMinimoStock());
                newStockProducto.setProducto(productoToUpdate);
                productoToUpdate.setStockProducto(newStockProducto);
            }

            productoService.updateProducto(productoToUpdate);
        }
        return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
    }
	

	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id) {
		productoService.deleteProducto(id);
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	
	 @GetMapping("/producto/partial/{productoId}")
	    public String getProductoPartial(@PathVariable int productoId, Model model) {
	        Optional<Producto> producto = productoService.findById(productoId);
	        if (producto.isPresent()) {
	            model.addAttribute("producto", producto.get());
	            return "fragments/productoDetalle :: producto";
	        } else {
	            return "error";
	        }
	    }




}
