package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendProyectoCorte2.ProyectoCorte2.Model.ValoracionModel;
import com.backendProyectoCorte2.ProyectoCorte2.Repository.IValoracionRepository; 
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@Service
public class ValoracionServiceImp implements IValoracionService {

    @Autowired
    private IValoracionRepository valoracionRepository; 

    @Override
    public String guardarValoracion(ValoracionModel valoracion) {
        if (esValoracionValida(valoracion.getValoracion_Estrellas())) {
            valoracionRepository.save(valoracion);
            return "La valoración con id " + valoracion.getIdValoracion() + " fue creada con éxito.";
        } else {
            throw new IllegalArgumentException("La valoración debe estar entre 1 y 5.");
        }
    }

    @Override
    public ValoracionModel buscarValoracionporId(int idValoracion) {
        Optional<ValoracionModel> valoracionEncontrada = valoracionRepository.findById(idValoracion);
        return valoracionEncontrada.orElseThrow(() -> 
            new RecursoNoEncontradoException("La valoración con el id " + idValoracion + " no fue encontrada.")
        );
    }

    @Override
    public List<ValoracionModel> listarValoracion() {
        return valoracionRepository.findAll();
    }

    @Override
    public String eliminarValoracionPorId(int idValoracion) {
        Optional<ValoracionModel> valoracionEncontrada = valoracionRepository.findById(idValoracion);
        if (valoracionEncontrada.isPresent()) {
            valoracionRepository.delete(valoracionEncontrada.get());
            return "La valoración con id " + idValoracion + " fue eliminada con éxito.";
        } else {
            throw new RecursoNoEncontradoException("Valoración no encontrada con el id " + idValoracion);
        }
    }

    @Override
    public String actualizarValoracion(int idValoracion, ValoracionModel valoracionNueva) {
        Optional<ValoracionModel> valoracionEncontrada = valoracionRepository.findById(idValoracion);
        if (valoracionEncontrada.isPresent()) {
            ValoracionModel valoracion = valoracionEncontrada.get();
            
            if (esValoracionValida(valoracionNueva.getValoracion_Estrellas())) {
                valoracion.setFechaValoracion(valoracionNueva.getFechaValoracion());
                valoracion.setValoracion_Estrellas(valoracionNueva.getValoracion_Estrellas());
                valoracion.setUsuarioId(valoracionNueva.getUsuarioId());
                valoracion.setDocumentoId(valoracionNueva.getDocumentoId());

                valoracionRepository.save(valoracion);
                return "La valoración con id " + valoracionNueva.getIdValoracion() + " fue actualizada con éxito.";
            } else {
                throw new IllegalArgumentException("La valoración debe estar entre 1 y 5.");
            }
        } else {
            throw new RecursoNoEncontradoException("Valoración no encontrada con el id " + idValoracion);
        }
    }

    // Método para validar la valoración
    private boolean esValoracionValida(Integer valoracion) {
        return valoracion != null && valoracion >= 1 && valoracion <= 5;
    }
}
