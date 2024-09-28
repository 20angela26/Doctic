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

import com.backendProyectoCorte2.ProyectoCorte2.Model.PublicacionModel;
import com.backendProyectoCorte2.ProyectoCorte2.Services.IPublicacionService;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@RestController
@RequestMapping("/publicacionController")
public class PublicacionController {

    @Autowired
    IPublicacionService publicacionService;

    // Crear nueva publicación
    @PostMapping("/post")
    public ResponseEntity<String> crearPublicacion(@RequestBody PublicacionModel Publicacion) {
        String respuesta = publicacionService.guardarPublicacion(Publicacion);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    // Obtener publicación por ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> obtenerPublicacionPorId(@PathVariable int IdPublicacion) {
        try {
            PublicacionModel Publicacion = publicacionService.buscarPublicacionporId(IdPublicacion);
            return ResponseEntity.ok(Publicacion);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Listar todas las publicaciones
    @GetMapping("/getAll")
    public ResponseEntity<List<PublicacionModel>> listarPublicaciones() {
        List<PublicacionModel> publicaciones = publicacionService.listarPublicacion();
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    // Eliminar publicación por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarPublicacion(@PathVariable int IdPublicacion) {
        try {
            String respuesta = publicacionService.eliminarPubliacionporId(IdPublicacion);
            return ResponseEntity.ok(respuesta);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Actualizar publicación por ID
    @PutMapping("/put/{id}")
    public ResponseEntity<String> actualizarPublicacion(@PathVariable int IdPublicacion, @RequestBody PublicacionModel publicacionNueva) {
        try {
            String respuesta = publicacionService.actualizarPublicacion(IdPublicacion, publicacionNueva);
            return ResponseEntity.ok(respuesta);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
