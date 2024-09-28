package com.backendProyectoCorte2.ProyectoCorte2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendProyectoCorte2.ProyectoCorte2.Model.CategoriaModel;
import com.backendProyectoCorte2.ProyectoCorte2.Services.ICategoriaService;

@RestController
@RequestMapping("/CategoriaController")
public class CategoriaController {

    @Autowired
    private ICategoriaService categoriaService;

    
    @PostMapping("/guardar")
    public ResponseEntity<String> guardarCategoria(@RequestBody CategoriaModel categoria) {
        String response = categoriaService.guardarCategoria(categoria);
        return ResponseEntity.ok(response);
    }

   
    @GetMapping("/buscar/{id}")
    public ResponseEntity<CategoriaModel> buscarCategoriaporId(@PathVariable("id") int idCategoria) {
        CategoriaModel categoria = categoriaService.buscarCategoriaporId(idCategoria);
        return ResponseEntity.ok(categoria);
    }

    
    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaModel>> listarCategoria() {
        List<CategoriaModel> categorias = categoriaService.listarCategoria();
        return ResponseEntity.ok(categorias);
    }

    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCategoriaPorId(@PathVariable("id") int idCategoria) {
        String response = categoriaService.eliminarCategoriaPorId(idCategoria);
        return ResponseEntity.ok(response);
    }

    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarCategoria(@PathVariable("id") int idCategoria, @RequestBody CategoriaModel categoriaNueva) {
        String response = categoriaService.actualizarCategoria(idCategoria, categoriaNueva);
        return ResponseEntity.ok(response);
    }
}
