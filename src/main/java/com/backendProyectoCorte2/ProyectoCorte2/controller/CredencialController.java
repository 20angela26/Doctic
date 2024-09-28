package com.backendProyectoCorte2.ProyectoCorte2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backendProyectoCorte2.ProyectoCorte2.Model.CredencialModel;
import com.backendProyectoCorte2.ProyectoCorte2.Services.ICredencialService;

@RestController
@RequestMapping("/api/credenciales")
public class CredencialController {

    @Autowired
    ICredencialService credencialService;

    // Crear una nueva credencial
    @PostMapping("/guardar")
    public ResponseEntity<String> guardarCredencial(@RequestBody CredencialModel credencial) {
        String respuesta = credencialService.guardarCredencial(credencial);
        return ResponseEntity.ok(respuesta);
    }

    // Obtener una credencial por Id
    @GetMapping("/{id}")
    public ResponseEntity<CredencialModel> buscarCredencialPorId(@PathVariable("id") int id) {
        CredencialModel credencial = credencialService.buscarCredencialporId(id);
        return ResponseEntity.ok(credencial);
    }

    // Listar todas las credenciales
    @GetMapping("/listar")
    public ResponseEntity<List<CredencialModel>> listarCredenciales() {
        List<CredencialModel> credenciales = credencialService.listarCredencial();
        return ResponseEntity.ok(credenciales);
    }

    // Eliminar una credencial por Id
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCredencialPorId(@PathVariable("id") int id) {
        String respuesta = credencialService.eliminarcCredencialPorId(id);
        return ResponseEntity.ok(respuesta);
    }

    // Actualizar una credencial por Id
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarCredencial(
            @PathVariable("id") int id, 
            @RequestBody CredencialModel credencialNueva) {
        String respuesta = credencialService.actualizarCredencial(id, credencialNueva);
        return ResponseEntity.ok(respuesta);
    }
}
