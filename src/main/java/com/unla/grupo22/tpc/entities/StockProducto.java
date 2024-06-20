package com.unla.grupo22.tpc.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class StockProducto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStock;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn (name = "id_producto", nullable=false)
    private Producto producto;
	
	private int cantidad;
	
	private int minimoStock;
	
	private int cantidadReabastecimiento; 

	public StockProducto(Producto producto, int cantidad, int minimoStock) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.minimoStock = minimoStock;
	}

}
