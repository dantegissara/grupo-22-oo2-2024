package com.unla.grupo22.tpc.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="detalle_compra")
@Getter @Setter @NoArgsConstructor
public class DetalleCompra {

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @ManyToOne
	    @JoinColumn(name = "compra_id", nullable = false)
	    private Compra compra;

	    @ManyToOne
	    @JoinColumn(name = "producto_id", nullable = false)
	    private Producto producto;

	    @Column(nullable = false)
	    private int cantidad;

	    public DetalleCompra(Compra compra, Producto producto, int cantidad) {
	        this.compra = compra;
	        this.producto = producto;
	        this.cantidad = cantidad;
	    }
	
}
