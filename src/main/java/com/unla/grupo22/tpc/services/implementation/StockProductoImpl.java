package com.unla.grupo22.tpc.services.implementation;

import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.entities.StockProducto;
import com.unla.grupo22.tpc.repositories.IStockProductoRepository;
import com.unla.grupo22.tpc.service.IStockProductoService;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service("stockProductoService")
public class StockProductoImpl implements IStockProductoService {

    private final IStockProductoRepository stockProductoRepository;
    
    public StockProductoImpl(IStockProductoRepository stockProductoRepository) {
        this.stockProductoRepository = stockProductoRepository;
    }
    
    @Override
    public List<StockProducto> obtenerTodosLosProductos() {
    
        return stockProductoRepository.findAll();
    }
    
    @Override
    public Optional<StockProducto> findByProducto(Producto producto) {
    	return stockProductoRepository.findByProducto(producto);
    }

    @Override
    public StockProducto guardar(StockProducto stockProducto) {
        return stockProductoRepository.save(stockProducto);
    }

    @Override
    public StockProducto actualizar(StockProducto stockProducto) {
        return stockProductoRepository.save(stockProducto);
    }
}
