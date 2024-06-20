package com.unla.grupo22.tpc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unla.grupo22.tpc.entities.PedidoAprovisionamiento;

public interface IReabastecimientoRepository extends JpaRepository<PedidoAprovisionamiento, Integer> {
}
