package com.unla.grupo22.tpc.services.implementation;

import org.springframework.stereotype.Service;

import com.unla.grupo22.tpc.entities.Lote;
import com.unla.grupo22.tpc.repositories.ILoteRepository;

@Service("stockService")
public class StockService {
	
	private final ILoteRepository loteRepository;

	public StockService(ILoteRepository loteRepository) {
		super();
		this.loteRepository = loteRepository;
	}
	
	public Lote altaNuevoLote(Lote lote) {
		return loteRepository.save(lote);
	}
	
	
}
