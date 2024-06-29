package com.unla.grupo22.tpc.service;

import java.util.List;
import java.util.Optional;
import com.unla.grupo22.tpc.entities.Producto;

public interface IProductoService {
	

    public Producto createProducto(Producto producto);

    public Producto updateProducto(Producto producto);

    public void deleteProducto(int idProducto);

    public Optional<Producto> findById(int idProducto);

    public List<Producto> getAllProductos();
    
    public List<Producto> getProductosBajoStock();

}
