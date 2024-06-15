package com.unla.grupo22.tpc.repositories;

import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.entities.StockProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStockProductoRepository extends JpaRepository<StockProducto, Integer> {
	
    StockProducto findByProducto(Producto producto);
}
