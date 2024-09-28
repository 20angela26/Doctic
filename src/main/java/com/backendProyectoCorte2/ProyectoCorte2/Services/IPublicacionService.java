package com.backendProyectoCorte2.ProyectoCorte2.Services;



import java.util.List;

import com.backendProyectoCorte2.ProyectoCorte2.Model.PublicacionModel;

public interface IPublicacionService {
    String guardarPublicacion(PublicacionModel Publicacion);
    PublicacionModel buscarPublicacionporId(int IdPublicacion);
    List<PublicacionModel>listarPublicacion(); 
    String eliminarPubliacionporId(int IdPublicacion); 
    String actualizarPublicacion(int IdPublicacion, PublicacionModel PublicacionNueva); 
}
