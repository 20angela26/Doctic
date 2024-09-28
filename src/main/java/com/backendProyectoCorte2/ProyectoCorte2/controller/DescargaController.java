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

import com.backendProyectoCorte2.ProyectoCorte2.Model.DescargaModel;
import com.backendProyectoCorte2.ProyectoCorte2.Services.IDescargaService;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;


@RequestMapping("/descargas")
public class DescargaController {

    @Autowired
    IDescargaService descargaService;

    
    @PostMapping("/crear")
    public ResponseEntity<String> crearDescarga(@RequestBody DescargaModel descarga) {
        String respuesta = descargaService.guardarDescarga(descarga);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarDescargaporId(@PathVariable int IdDescarga) {
        try {
            DescargaModel Descarga = descargaService.buscarDescargaporId(IdDescarga);
            return ResponseEntity.ok(Descarga);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    
    @GetMapping("/listar")
    public ResponseEntity<List<DescargaModel>> listarDescarga() {
        List<DescargaModel> Descarga = descargaService.listarDescarga();
        return new ResponseEntity<>(Descarga, HttpStatus.OK);
    }

    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDescargaporId(@PathVariable int IdDescarga) {
        try {
            String respuesta = descargaService.eliminarDescargaporId(IdDescarga);
            return ResponseEntity.ok(respuesta);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarDescarga(@PathVariable int IdDescarga, @RequestBody DescargaModel descargaNueva) {
        try {
            String respuesta = descargaService.actualizarDescarga(IdDescarga, descargaNueva);
            return ResponseEntity.ok(respuesta);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
