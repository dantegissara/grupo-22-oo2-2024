package com.unla.grupo22.tpc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.service.IProductoService;
import com.unla.grupo22.tpc.services.implementation.ProductoService;

@Controller
@RequestMapping("/alerta")
public class AlertaStockController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/bajoStockPage")
    public String getBajoStockPage() {
        return "alerta/bajoStockPage";
    }

    @GetMapping("/bajoStock")
    @ResponseBody
    public List<Producto> getBajoStock() {
        return productoService.getProductosBajoStock();
    }

    @GetMapping("/tieneBajoStock")
    @ResponseBody
    public boolean tieneBajoStock() {
        return !productoService.getProductosBajoStock().isEmpty();
    }
}


