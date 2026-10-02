package br.com.nonna_ai.service;

import br.com.nonna_ai.dto.CategoriaRequest;
import br.com.nonna_ai.entity.Categoria;
import br.com.nonna_ai.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria criarCategoria(CategoriaRequest request) {
        Categoria categoria = new Categoria(UUID.randomUUID().toString(), request.getNome());
        categoriaRepository.salvar(categoria);
        return categoria;
    }

    public List<Categoria> buscarCategorias(int page, int size) {
        int offset = page * size;
        return categoriaRepository.buscarTodos(size, offset);
    }

    public Categoria atualizarCategoria(String id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("CATEGORIA NÃO ENCONTRADA"));
        
        categoria.setNome(request.getNome());
        categoriaRepository.atualizar(categoria);
        return categoria;
    }

    public void deletarCategoria(String id) {
        Categoria categoria = categoriaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("CATEGORIA NÃO ENCONTRADA"));
        categoriaRepository.deletar(categoria.getId());
    }
}
