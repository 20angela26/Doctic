package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendProyectoCorte2.ProyectoCorte2.Model.ComentarioModel;
import com.backendProyectoCorte2.ProyectoCorte2.Repository.IComentarioRepository;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@Service
public class ComentarioServiceImp implements IComentarioService {

    @Autowired
    IComentarioRepository comentarioRepository;

    @Override
    public String guardarComentario(ComentarioModel comentario) {
        comentarioRepository.save(comentario);
        return "El comentario con id " + comentario.getIdComentario() + " fue creado con éxito";
    }

    @Override
    public ComentarioModel buscarComentarioporId(int IdComentario) {
        Optional<ComentarioModel> comentarioEncontrado = comentarioRepository.findById(IdComentario);
        return comentarioEncontrado.orElseThrow(() -> new RecursoNoEncontradoException("El comentario con el id " + IdComentario + " no fue encontrado"));
    }

    @Override
    public List<ComentarioModel> listarComentario() {
        return comentarioRepository.findAll();
    }

    @Override
    public String eliminarComentarioPorId(int IdComentario) {
        Optional<ComentarioModel> comentarioEncontrado = comentarioRepository.findById(IdComentario);
        if (comentarioEncontrado.isPresent()) {
            comentarioRepository.delete(comentarioEncontrado.get());
            return "El comentario con id " + IdComentario + " fue eliminado con éxito";
        } else {
            throw new RecursoNoEncontradoException("Comentario no encontrado con el Id " + IdComentario);
        }
    }

    @Override
    public String actualizarComentario(int IdComentario, ComentarioModel comentarioNuevo) {
        Optional<ComentarioModel> comentarioEncontrado = comentarioRepository.findById(IdComentario);
        if (comentarioEncontrado.isPresent()) {
            ComentarioModel comentario = comentarioEncontrado.get();
            comentario.setFechaComentario(comentarioNuevo.getFechaComentario());
            comentario.setTextoComentario(comentarioNuevo.getTextoComentario());
            comentario.setComentario_IdComentario(comentarioNuevo.getComentario_IdComentario());
            comentario.setUsuarioId(comentarioNuevo.getUsuarioId());
            comentario.setDocumentoId(comentarioNuevo.getDocumentoId());

            comentarioRepository.save(comentario);
            return "El comentario con id " + comentarioNuevo.getIdComentario() + " fue actualizado con éxito";
        } else {
            throw new RecursoNoEncontradoException("Comentario no encontrado con el Id " + IdComentario);
        }
    }
}
