package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;

import com.backendProyectoCorte2.ProyectoCorte2.Model.UsuarioModel;

public interface  IUsuarioService {
    String guardarUsuario(UsuarioModel Usuario);
    UsuarioModel buscarUsuarioporId(int IdUsuario);
    List<UsuarioModel>listarUsuario(); 
    String eliminarUsuarioPorId(int IdUsuario); 
    String actualizarUsuario(int IdUsuario, UsuarioModel UsuarioNuevo); 
}
