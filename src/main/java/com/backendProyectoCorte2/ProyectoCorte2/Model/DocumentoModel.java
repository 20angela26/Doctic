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
@Table(name="Documento")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DocumentoModel {
    @Id
    private Integer IdDocumento;
    private String Nombre_del_documento;
    private  LocalDateTime Fecha_de_Documento;
    private String URL;
    
@Enumerated(EnumType.STRING)
    private Visibilidad visibilidad;

    public enum Visibilidad {
        PUBLICO,PRIVADO
    }

}
