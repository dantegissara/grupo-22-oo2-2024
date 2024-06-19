package com.unla.grupo22.tpc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.grupo22.tpc.service.IStockProductoService;

@Controller
@RequestMapping("/informe")
public class InformeController {

    @Autowired
    private IStockProductoService stockProductoService;

    public InformeController(IStockProductoService stockProductoService) {
        this.stockProductoService = stockProductoService;
    }

    @GetMapping("/informes1")
    public String verInformesDeStock(Model model) {
        model.addAttribute("stocks", stockProductoService.obtenerTodosLosProductos());
        return "informe/informes1";
    }
}
