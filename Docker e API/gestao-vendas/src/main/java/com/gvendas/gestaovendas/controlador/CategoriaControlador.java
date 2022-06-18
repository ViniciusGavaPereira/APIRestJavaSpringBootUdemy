package com.gvendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gvendas.gestaovendas.entidades.Categoria;
import com.gvendas.gestaovendas.servico.CategoriaServico;

import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/categoria")
public class CategoriaControlador {

    @Autowired
    private CategoriaServico categoriaServico;

    @GetMapping
    public List<Categoria> listarTodos() {
        return categoriaServico.listarTodos();
    }

    @GetMapping(path = "/{codigo}")
    public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long codigo) {
        Optional<Categoria> categoria = categoriaServico.buscarId(codigo);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria);

        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){
        Categoria resp = categoriaServico.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping(path = "/{codigo}")
    public ResponseEntity<Categoria> atualizar( @PathVariable Long codigo, @Valid @RequestBody Categoria categoria){

        return ResponseEntity.ok(categoriaServico.atualizar(codigo, categoria));
    }

    
}
