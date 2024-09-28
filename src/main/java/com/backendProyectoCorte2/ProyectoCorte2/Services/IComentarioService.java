package com.backendProyectoCorte2.ProyectoCorte2.Services;



import java.util.List;

import com.backendProyectoCorte2.ProyectoCorte2.Model.ComentarioModel;

public interface IComentarioService {
    String guardarComentario(ComentarioModel Comentario);
    ComentarioModel buscarComentarioporId(int IdComentario);
    List<ComentarioModel>listarComentario(); 
    String eliminarComentarioPorId(int IdComentario); 
    String actualizarComentario(int IdComentario,ComentarioModel ComentarioNuevo); 
}
