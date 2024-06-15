package com.unla.grupo22.tpc.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo22.tpc.entities.Lote;

@Repository
public interface ILoteRepository extends JpaRepository<Lote, Serializable> {

}
