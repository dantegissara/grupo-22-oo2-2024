package com.unla.grupo22.tpc.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	@OneToOne(mappedBy = "stockProducto")
    private Producto producto;
	
	private int cantidad;
	
	private int minimoStock;

	public StockProducto(Producto producto, int cantidad, int minimoStock) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.minimoStock = minimoStock;
	}
	
	
	


}
