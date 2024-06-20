package com.unla.grupo22.tpc.controllers;

import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.unla.grupo22.tpc.entities.PedidoAprovisionamiento;

import com.unla.grupo22.tpc.service.IReabastecimientoService;
@Controller
public class ReabastecimientoController {
	
	private IReabastecimientoService reabastecimientoService;

	@PostMapping("/reabastecimientos")
	public PedidoAprovisionamiento crearReabastecimiento(@RequestBody PedidoAprovisionamiento reabastecimiento) {
		reabastecimiento.setFecha(LocalDate.now());
		return reabastecimientoService.saveReabastecimiento(reabastecimiento);
	}

	@GetMapping("/reabastecimientos/{id}")
	public PedidoAprovisionamiento obtenerReabastecimiento(@PathVariable int id) {
		return reabastecimientoService.getReabastecimientoById(id);
	}
}
