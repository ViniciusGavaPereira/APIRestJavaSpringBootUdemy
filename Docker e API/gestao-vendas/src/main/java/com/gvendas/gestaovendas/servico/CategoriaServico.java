package com.gvendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.repositorio.CategoriaRepositorio;

@Service
public class CategoriaServico {

    @Autowired()
    private CategoriaRepositorio categoriaRepositorio;

    public List<Categoria> listarTodos() {
        return categoriaRepositorio.findAll();

    }

    public Optional<Categoria> buscarId(Long codigo) {
        return categoriaRepositorio.findById(codigo);
    }

    public Categoria salvar(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    public Categoria atualizar(long codigo, Categoria categoria) {
        Categoria categoriaSalvar = validarCategoria(codigo);

        // Essa propriedade pega o parametro 'categoria', e sobrescreve ele sobre o
        // 'categoriaSalvar',
        // ou seja, agora 'categoriaSalvar' tem as informações de categoria
        BeanUtils.copyProperties(categoria, categoriaSalvar, "codigo");

        // Agora quando salvamos de novo, salvamos com informações novas
        return categoriaRepositorio.save(categoriaSalvar);

    }

    private Categoria validarCategoria(Long codigo) {
        Optional<Categoria> resp = buscarId(codigo);

        if (resp.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        } else {
            return resp.get();

        }

    }
}
