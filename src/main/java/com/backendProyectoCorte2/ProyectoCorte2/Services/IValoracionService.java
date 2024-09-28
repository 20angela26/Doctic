package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;

import com.backendProyectoCorte2.ProyectoCorte2.Model.ValoracionModel;

public interface  IValoracionService {
    String guardarValoracion(ValoracionModel Valoracion);
    ValoracionModel buscarValoracionporId(int IdValoracion);
    List<ValoracionModel>listarValoracion(); 
    String eliminarValoracionPorId(int IdValoracion); 
    String actualizarValoracion(int IdValoracion, ValoracionModel ValoracionNueva);
}
