package com.unla.grupo22.tpc.service;

import java.util.List;

import com.unla.grupo22.tpc.entities.PedidoAprovisionamiento;

public interface IReabastecimientoService {
	PedidoAprovisionamiento saveReabastecimiento(PedidoAprovisionamiento reabastecimiento);

	List<PedidoAprovisionamiento> getAllReabastecimientos();

	PedidoAprovisionamiento getReabastecimientoById(int id);

	void deleteReabastecimiento(int id);
}