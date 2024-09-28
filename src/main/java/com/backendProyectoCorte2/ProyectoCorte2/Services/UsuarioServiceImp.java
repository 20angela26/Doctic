package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendProyectoCorte2.ProyectoCorte2.Model.UsuarioModel;
import com.backendProyectoCorte2.ProyectoCorte2.Repository.IUsuarioRepository;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Override
    public String guardarUsuario(UsuarioModel usuario) {
        usuarioRepository.save(usuario);
        return "El usuario " + usuario.getNombre_de_Usuario() + " con id " + usuario.getIdUsuario() + " fue creado con éxito";
    }

    @Override
    public UsuarioModel buscarUsuarioporId(int IdUsuario) {
        Optional<UsuarioModel> usuarioEncontrado = usuarioRepository.findById(IdUsuario);
        return usuarioEncontrado.orElseThrow(() -> new RecursoNoEncontradoException("El usuario con el id " + IdUsuario+ " no fue encontrado"));
    }

    @Override
    public List<UsuarioModel> listarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public String eliminarUsuarioPorId(int id) {
        Optional<UsuarioModel> usuarioEncontrado = usuarioRepository.findById(id);
        if (usuarioEncontrado.isPresent()) {
            usuarioRepository.delete(usuarioEncontrado.get());
            return "El usuario con id " + id + " fue eliminado con éxito";
        } else {
            throw new RecursoNoEncontradoException("Usuario no encontrado con el Id " + id);
        }
    }

    @Override
    public String actualizarUsuario(int IdUsuario, UsuarioModel usuarioNuevo) {
        Optional<UsuarioModel> usuarioEncontrado = usuarioRepository.findById(IdUsuario);
        if (usuarioEncontrado.isPresent()) {
            UsuarioModel usuario = usuarioEncontrado.get();
            usuario.setNombre_de_Usuario (usuarioNuevo.getNombre_de_Usuario());
            usuario.setIdUsuario(usuarioNuevo.getIdUsuario());
            usuario.setCorreo_electronico(usuarioNuevo.getCorreo_electronico());
            usuario.setPregunta_secreta(usuarioNuevo.getPregunta_secreta());
            usuario.setLugar_de_origen(usuarioNuevo.getLugar_de_origen());
            usuarioRepository.save(usuario);
            return "El usuario con id " + usuarioNuevo.getIdUsuario() + " fue actualizado con éxito";
        } else {
            throw new RecursoNoEncontradoException("Usuario no encontrado con el Id " + IdUsuario);
        }
    }
}
