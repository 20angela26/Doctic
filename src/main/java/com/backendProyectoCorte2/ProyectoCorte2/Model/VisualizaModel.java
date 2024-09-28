package com.backendProyectoCorte2.ProyectoCorte2.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="Visualiza")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class VisualizaModel {
    @Id
    private Integer IdVisualiza;
    private LocalDateTime FechaVisualiza;

    @ManyToOne
    @JoinColumn(name="IdUsuario")
    private UsuarioModel UsuarioId;

    @ManyToOne
    @JoinColumn(name="IdDocumento")
    private DocumentoModel DocumentoId;
    
}
