package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.JwtService;
import com.example.demo.service.UsuarioService;
import com.example.demo.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/registro")
    @Operation(summary = "Registrar un usuario nuevo")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return usuarioService.registrarUsuario(usuario);
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuario")
    public Map<String, String> login(@RequestBody Usuario loginRequest) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(loginRequest.getEmail());
        Map<String, String> respuesta = new HashMap<>();

        if (usuarioEncontrado.isPresent()) {
            Usuario usuario = usuarioEncontrado.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
                String token = jwtService.generarToken(usuario.getEmail());

                respuesta.put("token", token);
                respuesta.put("rol", usuario.getRol());
                respuesta.put("nombre", usuario.getNombre());

                return respuesta;
            }
        }

        respuesta.put("mensaje", "Credenciales incorrectas");
        return respuesta;
    }

    @GetMapping
    @Operation(summary = "Listar usuarios")
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }
}