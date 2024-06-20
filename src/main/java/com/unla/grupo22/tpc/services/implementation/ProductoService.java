package com.unla.grupo22.tpc.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.repositories.IProductoRepository;
import com.unla.grupo22.tpc.service.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService {

	// @Autowired
	private IProductoRepository productoRepository;

	// private ModelMapper modelMapper = new ModelMapper();

	public ProductoService(IProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public Producto createProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public Producto updateProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public void deleteProducto(int idProducto) {
		productoRepository.deleteById(idProducto);
	}

	@Override
	public Optional<Producto> findById(int idProducto) { // optional
		return productoRepository.findById(idProducto);
	}

	@Override
	public List<Producto> getAllProductos() {
		return productoRepository.findAll();
	}

	@Override
	public List<Producto> getProductosBajoStock() {
		return productoRepository.findAll().stream().filter(
				producto -> producto.getStockProducto().getCantidad() < producto.getStockProducto().getMinimoStock())
				.collect(Collectors.toList());
	}

}
