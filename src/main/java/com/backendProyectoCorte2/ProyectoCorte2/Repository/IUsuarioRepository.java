package com.backendProyectoCorte2.ProyectoCorte2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendProyectoCorte2.ProyectoCorte2.Model.UsuarioModel;

public interface  IUsuarioRepository extends JpaRepository<UsuarioModel,Integer>{}