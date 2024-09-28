package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendProyectoCorte2.ProyectoCorte2.Model.VisualizaModel;
import com.backendProyectoCorte2.ProyectoCorte2.Repository.IVisualizaRepository;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@Service
public class VisualizaServiceImp implements IVisualizaService {

    @Autowired
    IVisualizaRepository visualizaRepository;

    @Override
    public String guardarVisualiza(VisualizaModel visualiza) {
        visualizaRepository.save(visualiza);
        return "La visualización con id " + visualiza.getIdVisualiza() + " fue creada con éxito";
    }

    @Override
    public VisualizaModel buscarVisualizaporId(int IdVisualiza) {
        Optional<VisualizaModel> visualizaEncontrada = visualizaRepository.findById(IdVisualiza);
        return visualizaEncontrada.orElseThrow(() -> new RecursoNoEncontradoException("La visualización con el id " + IdVisualiza + " no fue encontrada"));
    }

    @Override
    public List<VisualizaModel> listarVisualiza() {
        return visualizaRepository.findAll();
    }

    @Override
    public String eliminarVisualizaPorId(int IdVisualiza) {
        Optional<VisualizaModel> visualizaEncontrada = visualizaRepository.findById(IdVisualiza);
        if (visualizaEncontrada.isPresent()) {
            visualizaRepository.delete(visualizaEncontrada.get());
            return "La visualización con id " + IdVisualiza + " fue eliminada con éxito";
        } else {
            throw new RecursoNoEncontradoException("Visualización no encontrada con el Id " + IdVisualiza);
        }
    }

    @Override
    public String actualizarVisualiza(int IdVisualiza, VisualizaModel visualizaNuevo) {
        Optional<VisualizaModel> visualizaEncontrada = visualizaRepository.findById(IdVisualiza);
        if (visualizaEncontrada.isPresent()) {
            VisualizaModel visualiza = visualizaEncontrada.get();
            visualiza.setFechaVisualiza(visualizaNuevo.getFechaVisualiza());
            visualiza.setUsuarioId(visualizaNuevo.getUsuarioId());
            visualiza.setDocumentoId(visualizaNuevo.getDocumentoId());

            visualizaRepository.save(visualiza);
            return "La visualización con id " + visualizaNuevo.getIdVisualiza() + " fue actualizada con éxito";
        } else {
            throw new RecursoNoEncontradoException("Visualización no encontrada con el Id " + IdVisualiza);
        }
    }
}
