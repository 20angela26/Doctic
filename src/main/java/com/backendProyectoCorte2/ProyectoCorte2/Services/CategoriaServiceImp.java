package com.backendProyectoCorte2.ProyectoCorte2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendProyectoCorte2.ProyectoCorte2.Model.CategoriaModel;
import com.backendProyectoCorte2.ProyectoCorte2.Repository.ICategoriaRepository;
import com.backendProyectoCorte2.ProyectoCorte2.exception.RecursoNoEncontradoException;

@Service
public class CategoriaServiceImp implements ICategoriaService {

    @Autowired
    ICategoriaRepository categoriaRepository;

    @Override
    public String guardarCategoria(CategoriaModel categoria) {
        categoriaRepository.save(categoria);
        return "La categoría " + categoria.getNombreCategoria() + " con id " + categoria.getIdCategoria() + " fue creada con éxito";
    }

    @Override
    public CategoriaModel buscarCategoriaporId(int IdCategoria) {
        Optional<CategoriaModel> categoriaEncontrada = categoriaRepository.findById(IdCategoria);
        return categoriaEncontrada.orElseThrow(() -> new RecursoNoEncontradoException("La categoría con el id " + IdCategoria + " no fue encontrada"));
    }

    @Override
    public List<CategoriaModel> listarCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public String eliminarCategoriaPorId(int IdCategoria) {
        Optional<CategoriaModel> categoriaEncontrada = categoriaRepository.findById(IdCategoria);
        if (categoriaEncontrada.isPresent()) {
            categoriaRepository.delete(categoriaEncontrada.get());
            return "La categoría con id " + IdCategoria + " fue eliminada con éxito";
        } else {
            throw new RecursoNoEncontradoException("Categoría no encontrada con el Id " + IdCategoria);
        }
    }

    @Override
    public String actualizarCategoria(int IdCategoria, CategoriaModel categoriaNueva) {
        Optional<CategoriaModel> categoriaEncontrada = categoriaRepository.findById(IdCategoria);
        if (categoriaEncontrada.isPresent()) {
            CategoriaModel categoria = categoriaEncontrada.get();
            categoria.setNombreCategoria(categoriaNueva.getNombreCategoria());
            categoria.setCategoria_IdCategoria(categoriaNueva.getCategoria_IdCategoria());
            categoriaRepository.save(categoria);
            return "La categoría con id " + categoriaNueva.getIdCategoria() + " fue actualizada con éxito";
        } else {
            throw new RecursoNoEncontradoException("Categoría no encontrada con el Id " + IdCategoria);
        }
    }
}
