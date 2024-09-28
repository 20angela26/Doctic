package com.backendProyectoCorte2.ProyectoCorte2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendProyectoCorte2.ProyectoCorte2.Model.UsuarioModel;
import com.backendProyectoCorte2.ProyectoCorte2.Services.IUsuarioService;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@RestController
@RequestMapping("/Usuariocontroller")
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @PostMapping("/post")
    public ResponseEntity<String> crearUsuario(@RequestBody UsuarioModel usuario) {
        usuarioService.guardarUsuario(usuario);
        return new ResponseEntity<>(usuarioService.guardarUsuario(usuario), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable int IdUsuario) {
        try {
            UsuarioModel usuario = usuarioService.buscarUsuarioporId(IdUsuario);
            return ResponseEntity.ok(usuario);
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UsuarioModel>> listarUsuarios() {
        List<UsuarioModel> usuario = usuarioService.listarUsuario();
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarUsuarioPorId(@PathVariable int IdUsuario) {
        try {
            usuarioService.eliminarUsuarioPorId(IdUsuario);
            return ResponseEntity.ok().build();
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable int id, @RequestBody UsuarioModel usuario) {
        Object usuarioActualizado = usuarioService.actualizarUsuario(id, usuario);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok().body(usuarioActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
}
