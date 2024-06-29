package com.unla.grupo22.tpc.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unla.grupo22.tpc.entities.StockProducto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="codigo", unique=true, nullable=false, length=45)
	private String codigo;

	@Column(name="nombre", nullable=false, length=60)
	private String nombre;

	@Column(name="descripcion", nullable=false, length=150)
	private String descripcion;
	
	@Column(name = "costo")
	private int costo;
	
	@Column(name = "precio_de_venta")
	private int precioDeVenta;
	
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "producto", cascade = CascadeType.ALL)
	 @JsonIgnoreProperties("producto") // Ignore the "producto" field in StockProducto
    private StockProducto stockProducto;

	public Producto(String codigo, String nombre, String descripcion, int costo, int precioDeVenta,
			StockProducto stockProducto) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.precioDeVenta = precioDeVenta;
		this.stockProducto = stockProducto;
	}

	public Producto(String codigo, String nombre, String descripcion, int costo, int precioDeVenta) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costo = costo;
		this.precioDeVenta = precioDeVenta;
	}


}