package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;

import com.backendProyectoCorte2.ProyectoCorte2.Model.CredencialModel;

public interface ICredencialService {
    String guardarCredencial(CredencialModel Credencial);
    CredencialModel buscarCredencialporId(int IdCredencial);
    List<CredencialModel>listarCredencial(); 
    String eliminarcCredencialPorId(int IdCredencial); 
    String actualizarCredencial(int IdCredencial, CredencialModel CredencialNueva); 
}

