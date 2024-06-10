package com.unla.grupo22.tpc.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompra;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="producto_id", nullable=false)
	private Producto producto;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	private int cantidad;

	private LocalDate fechaCompra;

	public Compra(Producto producto, User user, int cantidad, LocalDate fechaCompra) {
		super();
		this.producto = producto;
		this.user = user;
		this.cantidad = cantidad;
		this.fechaCompra = fechaCompra;
	}
	

}
