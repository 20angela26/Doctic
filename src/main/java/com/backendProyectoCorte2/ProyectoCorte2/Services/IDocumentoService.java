package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;

import com.backendProyectoCorte2.ProyectoCorte2.Model.DocumentoModel;



public interface IDocumentoService {
     String guardarDocumento(DocumentoModel Documento);// Para crear registros en la tabla
    DocumentoModel buscarDocumentoporId(int IdDocumento);//Para ver un registro por id
    List<DocumentoModel>listarDocumento(); //Para ver todos los registros de la tabla
    String eliminarDocumentoPorId(int IdDocumento); //Para eliminar un registro por id
    String actualizarDocumento(int IdDocumento, DocumentoModel DocumentoNuevo); //Para actualizar registros de la tabla
}
