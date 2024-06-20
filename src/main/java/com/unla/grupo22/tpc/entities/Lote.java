package com.unla.grupo22.tpc.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Lote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLote;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="producto_id", nullable=false)
	private Producto producto;
	
	private int cantidadRecibida;
	
	private LocalDate fechaRecepcion;
	
	private String proveedor;
	
	private int precioDeCompra;

	public Lote(Producto producto, int cantidadRecibida, LocalDate fechaRecepcion, String proveedor,
			int precioDeCompra) {
		super();
		this.producto = producto;
		this.cantidadRecibida = cantidadRecibida;
		this.fechaRecepcion = fechaRecepcion;
		this.proveedor = proveedor;
		this.precioDeCompra = precioDeCompra;
	}
}
