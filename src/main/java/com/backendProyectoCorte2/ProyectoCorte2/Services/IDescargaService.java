package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;

import com.backendProyectoCorte2.ProyectoCorte2.Model.DescargaModel;


public interface IDescargaService {
     String guardarDescarga(DescargaModel Descarga);
     DescargaModel buscarDescargaporId(int IdDescarga);
     List<DescargaModel>listarDescarga(); 
    String eliminarDescargaporId(int IdDescarga); 
    String actualizarDescarga(int IdDescarga, DescargaModel DescargaNueva); 

    
}
