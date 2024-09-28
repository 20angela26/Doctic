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

import com.backendProyectoCorte2.ProyectoCorte2.Model.DocumentoModel;
import com.backendProyectoCorte2.ProyectoCorte2.Services.IDocumentoService;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;


@RestController
@RequestMapping("/DocumentoController ")
public class DocumentoController {

    @Autowired
    IDocumentoService documentoService;

    @PostMapping("/post")
    public ResponseEntity<String> crearDocumento(@RequestBody DocumentoModel Documento) {
        String respuesta = documentoService.guardarDocumento(Documento);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> buscarDocumentoPorId(@PathVariable int IdUsuario) {
        try {
            DocumentoModel Documento = documentoService.buscarDocumentoporId(IdUsuario);
            return ResponseEntity.ok(Documento);
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DocumentoModel>> listarDocumentos() {
        List<DocumentoModel> documentos = documentoService.listarDocumento();
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarDocumentoporId(@PathVariable int IdDocumento) {
        try {
            documentoService.eliminarDocumentoPorId(IdDocumento);
            return ResponseEntity.ok().build();
        } catch (RecursoNoEncontradoException e) {
            String mensajeError = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> actualizarDocumento(@PathVariable int IdDocumento, @RequestBody DocumentoModel Documento) {
        try {
            String respuesta = documentoService.actualizarDocumento(IdDocumento, Documento);
            return ResponseEntity.ok(respuesta);
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
