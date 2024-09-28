package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendProyectoCorte2.ProyectoCorte2.Model.DocumentoModel;
import com.backendProyectoCorte2.ProyectoCorte2.Repository.IDocumentoRepository;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;


@Service
public class DocumentoServiceImp implements IDocumentoService {

    @Autowired
    IDocumentoRepository documentoRepository;

    @Override
    public String guardarDocumento(DocumentoModel Documento) {
        documentoRepository.save(Documento);
        return "El documento " + Documento.getNombre_del_documento() + " con id " + Documento.getIdDocumento() + " fue creado con éxito";
    }

    @Override
    public DocumentoModel buscarDocumentoporId(int IdDocumento) {
        Optional<DocumentoModel> documentoEncontrado = documentoRepository.findById(IdDocumento);
        return documentoEncontrado.orElseThrow(() -> new RecursoNoEncontradoException("El documento con el id " + IdDocumento + " no fue encontrado"));
    }

    @Override
    public List<DocumentoModel> listarDocumento() {
        return documentoRepository.findAll();
    }

    @Override
    public String eliminarDocumentoPorId(int id) {
        Optional<DocumentoModel> documentoEncontrado = documentoRepository.findById(id);
        if (documentoEncontrado.isPresent()) {
            documentoRepository.delete(documentoEncontrado.get());
            return "El documento con id " + id + " fue eliminado con éxito";
        } else {
            throw new RecursoNoEncontradoException("Documento no encontrado con el Id " + id);
        }
    }

    @Override
    public String actualizarDocumento(int IdDocumento, DocumentoModel documentoNuevo) {
        Optional<DocumentoModel> documentoEncontrado = documentoRepository.findById(IdDocumento);
        if (documentoEncontrado.isPresent()) {
            DocumentoModel documento = documentoEncontrado.get();
            documento.setNombre_del_documento(documentoNuevo.getNombre_del_documento());
            documento.setFecha_de_Documento(documentoNuevo.getFecha_de_Documento());
            documento.setURL(documentoNuevo.getURL());
            documento.setVisibilidad(documentoNuevo.getVisibilidad());

            documentoRepository.save(documento);
            return "El documento con id " + documentoNuevo.getIdDocumento() + " fue actualizado con éxito";
        } else {
            throw new RecursoNoEncontradoException("Documento no encontrado con el Id " + IdDocumento);
        }
    }
}
