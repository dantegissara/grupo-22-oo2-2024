package com.unla.grupo22.tpc.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.unla.grupo22.tpc.entities.Compra;
import com.unla.grupo22.tpc.entities.DetalleCompra;
import com.unla.grupo22.tpc.entities.Producto;
import com.unla.grupo22.tpc.entities.User;
import com.unla.grupo22.tpc.services.implementation.CompraService;
import com.unla.grupo22.tpc.services.implementation.ProductoService;
import com.unla.grupo22.tpc.services.implementation.UserService;

@Controller
@RequestMapping("/compras")
public class CompraController {

    private CompraService compraService;
    private ProductoService productoService;
    private UserService userService;

    public CompraController(CompraService compraService, ProductoService productoService, UserService userService) {
        super();
        this.compraService = compraService;
        this.productoService = productoService;
        this.userService = userService;
    }

    @GetMapping("/nueva")
    public String mostrarFormularioCompra(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        return "compras/nueva";
    }

    @PostMapping("/realizar")
    public String realizarCompra(
            @RequestParam("userId") int userId,
            @RequestParam("productosIds") List<Integer> productosIds,
            @RequestParam("cantidades") List<Integer> cantidades,
            RedirectAttributes redirectAttributes) {

        try {
            System.out.println("Productos IDs: " + productosIds);
            System.out.println("Cantidades: " + cantidades);

            User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User no encontrado"));

            // Asegúrate de que ambos listas tengan el mismo tamaño
            if (productosIds.size() != cantidades.size()) {
                throw new RuntimeException("El número de productos no coincide con el número de cantidades");
            }

            List<DetalleCompra> detallesCompra = new ArrayList<>();
            for (int i = 0; i < productosIds.size(); i++) {
                Producto producto = productoService.findById(productosIds.get(i)).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
                detallesCompra.add(new DetalleCompra(null, producto, cantidades.get(i)));
            }

            Compra compra = new Compra(user, detallesCompra);
            detallesCompra.forEach(detalle -> detalle.setCompra(compra));

            compraService.realizarCompra(compra);

            redirectAttributes.addFlashAttribute("success", "Compra realizada con éxito");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al realizar la compra: " + e.getMessage());
        }

        return "redirect:/compras/nueva";
    }

}
