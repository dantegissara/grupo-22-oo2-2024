package com.unla.grupo22.tpc.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unla.grupo22.tpc.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable> { // En lugar de Integer colocar Serializable ?
	
// find by name ?
// find by ... ?
// querys personalizados

}
