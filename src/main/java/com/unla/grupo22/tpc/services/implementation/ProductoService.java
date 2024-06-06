package com.unla.grupo22.tpc.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.repositories.IProductoRepository;
import com.unla.grupo22.tpc.service.IProductoService;

@Service
public class ProductoService implements IProductoService{

	//@Autowired ?
    private IProductoRepository productoRepository;

    @Override
    @Transactional
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto updateProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void deleteProducto(int idProducto) {
        productoRepository.deleteById(idProducto);
    }

    @Override
    @Transactional(readOnly = true) // indica que no se haran cambios en la BD
    public Optional<Producto> findById(int idProducto) { // optional 
        return productoRepository.findById(idProducto);
    }

    @Override
    @Transactional(readOnly = true) // indica que no se haran cambios en la BD
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

}
