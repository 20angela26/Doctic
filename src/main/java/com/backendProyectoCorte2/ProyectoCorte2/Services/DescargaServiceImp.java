package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendProyectoCorte2.ProyectoCorte2.Model.DescargaModel;
import com.backendProyectoCorte2.ProyectoCorte2.Repository.IDescargaRepository;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;


@Service
public class DescargaServiceImp implements IDescargaService {

    @Autowired
    IDescargaRepository descargaRepository;

    @Override
    public String guardarDescarga(DescargaModel Descarga) {
        descargaRepository.save(Descarga);
        return "La Descarga con id " + Descarga.getIdDescarga() + " fue creada con éxito";
    }

    @Override
    public DescargaModel buscarDescargaporId(int IdDescarga) {
        Optional<DescargaModel> descargaEncontrada = descargaRepository.findById(IdDescarga);
        return descargaEncontrada.orElseThrow(() -> new RecursoNoEncontradoException("La descarga con el id " + IdDescarga + " no fue encontrada"));
    }

    @Override
    public List<DescargaModel> listarDescarga() {
        return descargaRepository.findAll();
    }

    @Override
    public String eliminarDescargaporId(int IdDescarga) {
        Optional<DescargaModel> descargaEncontrada = descargaRepository.findById(IdDescarga);
        if (descargaEncontrada.isPresent()) {
            descargaRepository.delete(descargaEncontrada.get());
            return "La descarga con id " + IdDescarga + " fue eliminada con éxito";
        } else {
            throw new RecursoNoEncontradoException("Descarga no encontrada con el Id " + IdDescarga);
        }
    }

    @Override
    public String actualizarDescarga(int IdDescarga,DescargaModel descargaNueva) {
        Optional<DescargaModel> descargaEncontrada = descargaRepository.findById(IdDescarga);
        if (descargaEncontrada.isPresent()) {
            DescargaModel descarga = descargaEncontrada.get();
            descarga.setFechaDescarga(descargaNueva.getFechaDescarga());
            descarga.setUsuarioId(descargaNueva.getUsuarioId());
            descarga.setDocumentoId(descargaNueva.getDocumentoId());

            descargaRepository.save(descarga);
            return "La descarga con id " + descargaNueva.getIdDescarga() + " fue actualizada con éxito";
        } else {
            throw new RecursoNoEncontradoException("Descarga no encontrada con el Id " + IdDescarga);
        }
    }
}


