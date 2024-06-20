package com.unla.grupo22.tpc.services.implementation;
import org.springframework.stereotype.Service;

import com.unla.grupo22.tpc.entities.StockProducto;
import com.unla.grupo22.tpc.repositories.IStockProductoRepository;

@Service
public class AlertaStock {

    private final IStockProductoRepository stockRepository;

    public AlertaStock(IStockProductoRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public String verificarStockBajo(int idProducto) {
        StockProducto stockProducto = stockRepository.findById(idProducto).orElse(null);
        if (stockProducto != null && stockProducto.getCantidad() <= stockProducto.getMinimoStock()) {
            return "Alerta: El stock del producto " + stockProducto.getProducto().getNombre() + " está bajo.";
        }
        return "El stock del producto está en niveles aceptables.";
    }
}

