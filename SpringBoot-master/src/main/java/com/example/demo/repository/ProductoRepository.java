package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoRepository {

    private List<Producto> productos = new ArrayList<>();

    public ProductoRepository() {

        String servidor = "http://98.80.98.199:8080";

        // 1. Pastel de Chocolate (Usando la foto local chocolate.jpg)
        productos.add(new Producto(1,
                "Pastel de Chocolate",
                "Pastel húmedo con cobertura de cacao.",
                5000.0,
                servidor + "/chocolate.jpg")); // <--- Asegúrate de renombrar el archivo largo a chocolate.jpg

        // 2. Cheesecake
        productos.add(new Producto(2,
                "Cheesecake",
                "Tarta de queso con base de galleta.",
                4500.0,
                servidor + "/cheesecake_frutilla.jpg"));

        // 3. Cupcake (Usando tu foto de cupcake)
        productos.add(new Producto(3,
                "Cupcake de Vainilla",
                "Suave y esponjoso con crema.",
                3500.0,
                servidor + "/cupcake_vainilla.jpg"));
    }

    public List<Producto> findAll() {
        return productos;
    }
}