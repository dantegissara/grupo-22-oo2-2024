package com.unla.grupo22.tpc.services.implementation;

import java.util.List;


import org.springframework.stereotype.Service;
import com.unla.grupo22.tpc.entities.PedidoAprovisionamiento;
import com.unla.grupo22.tpc.repositories.IReabastecimientoRepository;
import com.unla.grupo22.tpc.service.IReabastecimientoService;

@Service
public class ReabastecimientoService implements IReabastecimientoService {

    private final IReabastecimientoRepository reabastecimientoRepository;

    public ReabastecimientoService(IReabastecimientoRepository reabastecimientoRepository) {
        this.reabastecimientoRepository = reabastecimientoRepository;
    }

    @Override
    public PedidoAprovisionamiento saveReabastecimiento(PedidoAprovisionamiento reabastecimiento) {
        return reabastecimientoRepository.save(reabastecimiento);
    }

    @Override
    public List<PedidoAprovisionamiento> getAllReabastecimientos() {
        return reabastecimientoRepository.findAll();
    }

    @Override
    public PedidoAprovisionamiento getReabastecimientoById(int id) {
        return reabastecimientoRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteReabastecimiento(int id) {
        reabastecimientoRepository.deleteById(id);
    }
}
