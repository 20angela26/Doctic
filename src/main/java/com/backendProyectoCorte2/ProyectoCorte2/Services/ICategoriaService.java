package com.backendProyectoCorte2.ProyectoCorte2.Services;



import java.util.List;

import com.backendProyectoCorte2.ProyectoCorte2.Model.CategoriaModel;

public interface ICategoriaService {
    String guardarCategoria(CategoriaModel Categoria);
    CategoriaModel buscarCategoriaporId(int IdCategoria);
    List<CategoriaModel>listarCategoria(); 
    String eliminarCategoriaPorId(int IdCategoria); 
    String actualizarCategoria(int IdCategoria, CategoriaModel CategoriaNueva); 
}
