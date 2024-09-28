package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendProyectoCorte2.ProyectoCorte2.Model.PublicacionModel;
import com.backendProyectoCorte2.ProyectoCorte2.Repository.IPublicacionRepository;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;


@Service
public class PublicacionServiceImp implements IPublicacionService {

    @Autowired
    IPublicacionRepository publicacionRepository;

    @Override
    public String guardarPublicacion(PublicacionModel Publicacion) {
        publicacionRepository.save(Publicacion);
        return "La publicación con id " + Publicacion.getIdPublicacion() + " fue creada con éxito";
    }

    @Override
    public PublicacionModel buscarPublicacionporId(int idPublicacion) {
        Optional<PublicacionModel> publicacionEncontrada = publicacionRepository.findById(idPublicacion);
        return publicacionEncontrada.orElseThrow(() -> new RecursoNoEncontradoException("La publicación con el id " + idPublicacion + " no fue encontrada"));
    }

    @Override
    public List<PublicacionModel> listarPublicacion() {
        return publicacionRepository.findAll();
    }

    @Override
    public String eliminarPubliacionporId(int IdPublicacion) {
        Optional<PublicacionModel> publicacionEncontrada = publicacionRepository.findById(IdPublicacion);
        if (publicacionEncontrada.isPresent()) {
            publicacionRepository.delete(publicacionEncontrada.get());
            return "La publicación con id " + IdPublicacion + " fue eliminada con éxito";
        } else { 
            throw new RecursoNoEncontradoException("Publicación no encontrada con el Id " + IdPublicacion);
        }
    }

    @Override
    public String actualizarPublicacion(int IdPublicacion, PublicacionModel publicacionNueva) {
        Optional<PublicacionModel> publicacionEncontrada = publicacionRepository.findById(IdPublicacion);
        if (publicacionEncontrada.isPresent()) {
            PublicacionModel publicacion = publicacionEncontrada.get();
                publicacion.setFechaPublicacion(publicacionNueva.getFechaPublicacion());
                publicacion.setRol(publicacionNueva.getRol());
            
            publicacionRepository.save(publicacion);
            return "La publicación con id " + publicacionNueva.getIdPublicacion() + " fue actualizada con éxito";
        } else {
            throw new RecursoNoEncontradoException("Publicación no encontrada con el Id " + IdPublicacion);
        }
    }
}
