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
public class PedidoAprovisionamiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="producto_id", nullable=false)
	private Producto producto;

	private int cantidad;
	private LocalDate fecha;
	private String proveedor;
	
	public PedidoAprovisionamiento(Producto producto, int cantidad, LocalDate fecha, String proveedor) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.proveedor = proveedor;
	}
	
	

}
