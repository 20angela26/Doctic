package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendProyectoCorte2.ProyectoCorte2.Model.CredencialModel;
import com.backendProyectoCorte2.ProyectoCorte2.Repository.ICredencialRepository;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@Service
public class CredencialServiceImp implements ICredencialService {

    @Autowired
    ICredencialRepository credencialRepository;

    @Override
    public String guardarCredencial(CredencialModel credencial) {
        credencialRepository.save(credencial);
        return "La credencial con id " + credencial.getIdCredencial() + " fue creada con éxito";
    }

    @Override
    public CredencialModel buscarCredencialporId(int IdCredencial) {
        Optional<CredencialModel> credencialEncontrada = credencialRepository.findById(IdCredencial);
        return credencialEncontrada.orElseThrow(() -> new RecursoNoEncontradoException("La credencial con el id " + IdCredencial + " no fue encontrada"));
    }

    @Override
    public List<CredencialModel> listarCredencial() {
        return credencialRepository.findAll();
    }

    @Override
    public String eliminarcCredencialPorId(int IdCredencial) {
        Optional<CredencialModel> credencialEncontrada = credencialRepository.findById(IdCredencial);
        if (credencialEncontrada.isPresent()) {
            credencialRepository.delete(credencialEncontrada.get());
            return "La credencial con id " + IdCredencial + " fue eliminada con éxito";
        } else {
            throw new RecursoNoEncontradoException("Credencial no encontrada con el Id " + IdCredencial);
        }
    }

    @Override
    public String actualizarCredencial(int IdCredencial, CredencialModel credencialNueva) {
        Optional<CredencialModel> credencialEncontrada = credencialRepository.findById(IdCredencial);
        if (credencialEncontrada.isPresent()) {
            CredencialModel credencial = credencialEncontrada.get();
            credencial.setContraseñaUsuario(credencialNueva.getContraseñaUsuario());
            credencial.setEstado(credencialNueva.getEstado());
            credencial.setUsuarioId(credencialNueva.getUsuarioId());

            credencialRepository.save(credencial);
            return "La credencial con id " + credencialNueva.getIdCredencial() + " fue actualizada con éxito";
        } else {
            throw new RecursoNoEncontradoException("Credencial no encontrada con el Id " + IdCredencial);
        }
    }
}
