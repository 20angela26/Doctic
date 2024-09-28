package com.backendProyectoCorte2.ProyectoCorte2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name ="Categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategoriaModel {
    @Id
    private Integer IdCategoria;
    private String NombreCategoria;
    private Integer Categoria_IdCategoria;

    @ManyToOne
    @JoinColumn(name = "Categoria_idCategoria")
    private CategoriaModel Categoria;

}
