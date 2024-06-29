package com.unla.grupo22.tpc.services.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.grupo22.tpc.entities.Compra;
import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.entities.StockProducto;
import com.unla.grupo22.tpc.repositories.ICompraRepository;
import com.unla.grupo22.tpc.repositories.IStockProductoRepository;
import com.unla.grupo22.tpc.service.ICompraService;

import jakarta.transaction.Transactional;

@Service
public class CompraService implements ICompraService {

	private ICompraRepository compraRepository;
	private IStockProductoRepository stockProductoRepository;
	
	public CompraService(ICompraRepository compraRepository, IStockProductoRepository stockProductoRepository) {
		super();
		this.compraRepository = compraRepository;
		this.stockProductoRepository = stockProductoRepository;
	}
	
	@Override
	@Transactional
	public Compra realizarCompra(Compra compra) {
	    // Verificar que todos los detalles tienen el valor correcto
	    compra.getDetalles().forEach(detalle -> {
	        var stockProducto = stockProductoRepository.findByProducto(detalle.getProducto())
	                .orElseThrow(() -> new RuntimeException("Stock no encontrado para el producto: " + detalle.getProducto().getNombre()));
	        if (stockProducto.getCantidad() < detalle.getCantidad()) {
	            throw new RuntimeException("No hay suficiente stock para el producto: " + detalle.getProducto().getNombre());
	        }
	        stockProducto.setCantidad(stockProducto.getCantidad() - detalle.getCantidad());
	        stockProductoRepository.save(stockProducto);
	    });

	    // Guardar la compra
	    return compraRepository.save(compra);
	}

}
