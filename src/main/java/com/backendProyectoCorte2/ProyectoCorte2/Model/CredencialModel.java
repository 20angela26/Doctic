package com.backendProyectoCorte2.ProyectoCorte2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Credencial")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CredencialModel {
@Id
private  Integer IdCredencial;
private String ContraseñaUsuario;

@Enumerated(EnumType.STRING)
    private Estado estado;

    public enum Estado {
        Activo,Inactivo
    }
@ManyToOne
    @JoinColumn(name="IdUsuario")
    private UsuarioModel UsuarioId;


    
}
