package br.com.nonna_ai.controller;

import br.com.nonna_ai.dto.CategoriaRequest;
import br.com.nonna_ai.entity.Categoria;
import br.com.nonna_ai.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody CategoriaRequest request) {
        return new ResponseEntity<>(categoriaService.criarCategoria(request), HttpStatus.CREATED);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        if (size > 100) size = 100;
        return ResponseEntity.ok(categoriaService.buscarCategorias(page, size));
    }

    @PutMapping("/categoria/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(
            @PathVariable String id,
            @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaService.atualizarCategoria(id, request));
    }

    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable String id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
