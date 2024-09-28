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

import com.backendProyectoCorte2.ProyectoCorte2.Model.ComentarioModel;
import com.backendProyectoCorte2.ProyectoCorte2.Services.IComentarioService;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    @Autowired
    IComentarioService comentarioService;

    @PostMapping("/post")
    public ResponseEntity<String> crearComentario(@RequestBody ComentarioModel comentario) {
        String respuesta = comentarioService.guardarComentario(comentario);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @GetMapping("/getById/{IdComentario}")
    public ResponseEntity<?> buscarComentarioPorId(@PathVariable int IdComentario) {
        try {
            ComentarioModel comentario = comentarioService.buscarComentarioporId(IdComentario);
            return ResponseEntity.ok(comentario);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ComentarioModel>> listarComentarios() {
        List<ComentarioModel> comentarios = comentarioService.listarComentario();
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{IdComentario}")
    public ResponseEntity<?> eliminarComentarioPorId(@PathVariable int IdComentario) {
        try {
            String respuesta = comentarioService.eliminarComentarioPorId(IdComentario);
            return ResponseEntity.ok(respuesta);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/put/{IdComentario}")
    public ResponseEntity<?> actualizarComentario(@PathVariable int IdComentario, @RequestBody ComentarioModel comentario) {
        try {
            String respuesta = comentarioService.actualizarComentario(IdComentario, comentario);
            return ResponseEntity.ok(respuesta);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
