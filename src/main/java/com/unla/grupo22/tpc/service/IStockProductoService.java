package com.unla.grupo22.tpc.service;

import java.util.List;
import java.util.Optional;

import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.entities.StockProducto;

public interface IStockProductoService {

	Optional<StockProducto> findByProducto(Producto producto);

    StockProducto guardar(StockProducto stockProducto);

    StockProducto actualizar(StockProducto stockProducto);

    List<StockProducto> obtenerTodosLosProductos();
}
