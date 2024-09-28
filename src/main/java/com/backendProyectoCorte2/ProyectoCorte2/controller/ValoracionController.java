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

import com.backendProyectoCorte2.ProyectoCorte2.Model.ValoracionModel;
import com.backendProyectoCorte2.ProyectoCorte2.Services.IValoracionService;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@RestController
@RequestMapping("/valoracioncontroller")
public class ValoracionController {

    @Autowired
    private IValoracionService valoracionService;

    @PostMapping("/post")
    public ResponseEntity<String> crearValoracion(@RequestBody ValoracionModel Valoracion) {
        return new ResponseEntity<>(valoracionService.guardarValoracion(Valoracion), HttpStatus.CREATED);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> buscarValoracionPorId(@PathVariable int IdValoracion) {
        try {
            ValoracionModel Valoracion = valoracionService.buscarValoracionporId(IdValoracion);
            return ResponseEntity.ok(Valoracion);
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ValoracionModel>> listarValoraciones() {
        List<ValoracionModel> valoraciones = valoracionService.listarValoracion();
        return new ResponseEntity<>(valoraciones, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarValoracionPorId(@PathVariable int IdValoracion) {
        try {
            valoracionService.eliminarValoracionPorId(IdValoracion);
            return ResponseEntity.ok().build();
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> actualizarValoracion(@PathVariable int IdValoracion, @RequestBody ValoracionModel valoracionNueva) {
        try {
            String mensajeActualizado = valoracionService.actualizarValoracion(IdValoracion, valoracionNueva);
            return ResponseEntity.ok(mensajeActualizado);
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        } catch (IllegalArgumentException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
        }
    }
}
