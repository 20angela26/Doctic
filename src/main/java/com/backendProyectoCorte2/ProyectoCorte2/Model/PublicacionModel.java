package com.backendProyectoCorte2.ProyectoCorte2.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Publicacion")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PublicacionModel {
    @Id
    private Integer IdPublicacion;
    private LocalDateTime FechaPublicacion;
    
@Enumerated(EnumType.STRING)
    private ROL rol;

    public enum ROL {
        Autor,Coautor
    }
    
}
