package com.unla.grupo22.tpc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unla.grupo22.tpc.entities.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> { // En lugar de Integer colocar Serializable ?
	
// find by name ?
// find by ... ?
// querys personalizados

}
