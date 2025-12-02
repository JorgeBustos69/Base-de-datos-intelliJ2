package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin("*")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    // --- OBTENER TODOS ---
    @GetMapping
    public List<Producto> obtenerProductos() {
        return repository.findAll();
    }

    // --- OBTENER POR ID ---
    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable int id) {
        return repository.findAll()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // --- CREAR PRODUCTO ---
    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        repository.findAll().add(producto);
        return producto;
    }

    // --- EDITAR PRODUCTO ---
    @PutMapping("/{id}")
    public Producto editarProducto(@PathVariable int id, @RequestBody Producto producto) {

        for (Producto p : repository.findAll()) {
            if (p.getId() == id) {

                p.setNombre(producto.getNombre());
                p.setDescripcion(producto.getDescripcion());
                p.setPrecio(producto.getPrecio());
                p.setImagenUrl(producto.getImagenUrl());

                return p;
            }
        }
        return null;
    }

    // --- ELIMINAR PRODUCTO ---
    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable int id) {
        repository.findAll().removeIf(p -> p.getId() == id);
    }
}
