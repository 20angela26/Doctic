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

import com.backendProyectoCorte2.ProyectoCorte2.Model.VisualizaModel;
import com.backendProyectoCorte2.ProyectoCorte2.Services.IVisualizaService;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@RestController
@RequestMapping("/VisualizaController")
public class VisualizaController {

    @Autowired
    IVisualizaService visualizaService;

    @PostMapping("/post")
    public ResponseEntity<String> crearVisualiza(@RequestBody VisualizaModel visualiza) {
        visualizaService.guardarVisualiza(visualiza);
        return new ResponseEntity<>(visualizaService.guardarVisualiza(visualiza), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> buscarVisualizaPorId(@PathVariable int IdVisualiza) {
        try {
            VisualizaModel visualiza = visualizaService.buscarVisualizaporId(IdVisualiza);
            return ResponseEntity.ok(visualiza);
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<VisualizaModel>> listarVisualiza() {
        List<VisualizaModel> visualiza = visualizaService.listarVisualiza();
        return new ResponseEntity<>(visualiza, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarVisualizaPorId(@PathVariable int IdVisualiza) {
        try {
            visualizaService.eliminarVisualizaPorId(IdVisualiza);
            return ResponseEntity.ok().build();
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> actualizarVisualiza(@PathVariable int id, @RequestBody VisualizaModel visualiza) {
        Object visualizaActualizada = visualizaService.actualizarVisualiza(id, visualiza);
        if (visualizaActualizada != null) {
            return ResponseEntity.ok().body(visualizaActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visualización no encontrada");
        }
    }
}
