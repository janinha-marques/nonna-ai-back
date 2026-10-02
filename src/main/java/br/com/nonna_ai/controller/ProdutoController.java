package br.com.nonna_ai.controller;

import br.com.nonna_ai.dto.ProdutoRequest;
import br.com.nonna_ai.entity.Produto;
import br.com.nonna_ai.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody ProdutoRequest request) {
        return new ResponseEntity<>(produtoService.criarProduto(request), HttpStatus.CREATED);
    }

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> listarProdutos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        if (size > 100) size = 100;
        return ResponseEntity.ok(produtoService.buscarProdutos(page, size));
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable String id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable String id,
            @Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtoService.atualizarProduto(id, request));
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable String id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
