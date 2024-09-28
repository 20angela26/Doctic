package com.backendProyectoCorte2.ProyectoCorte2.Services;


import java.util.List;

import com.backendProyectoCorte2.ProyectoCorte2.Model.VisualizaModel;

public interface IVisualizaService {
    String guardarVisualiza(VisualizaModel Visualiza);
    VisualizaModel buscarVisualizaporId(int IdVisualiza);
    List<VisualizaModel>listarVisualiza(); 
    String eliminarVisualizaPorId(int IdVisualiza); 
    String actualizarVisualiza(int IdVisualiza, VisualizaModel VisualizaNuevo); 
}
