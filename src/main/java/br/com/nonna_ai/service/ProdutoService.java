package br.com.nonna_ai.service;

import br.com.nonna_ai.dto.ProdutoRequest;
import br.com.nonna_ai.entity.Produto;
import br.com.nonna_ai.repository.CategoriaRepository;
import br.com.nonna_ai.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto criarProduto(ProdutoRequest request) {
        if (categoriaRepository.buscarPorId(request.getCategoria()).isEmpty()) {
            throw new IllegalArgumentException("CATEGORIA NÃO ENCONTRADA");
        }

        Produto produto = new Produto();
        produto.setId(UUID.randomUUID().toString());
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setCategoria(request.getCategoria());
        produto.setImagem(request.getImagem());

        produtoRepository.salvar(produto);
        return produto;
    }

    public List<Produto> buscarProdutos(int page, int size) {
        int offset = page * size;
        return produtoRepository.buscarTodos(size, offset);
    }

    public Produto buscarProdutoPorId(String id) {
        return produtoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("PRODUTO NÃO ENCONTRADO"));
    }

    public Produto atualizarProduto(String id, ProdutoRequest request) {
        if (categoriaRepository.buscarPorId(request.getCategoria()).isEmpty()) {
            throw new IllegalArgumentException("CATEGORIA NÃO ENCONTRADA");
        }

        Produto produto = buscarProdutoPorId(id);
        produto.setNome(request.getNome());
        produto.setDescricao(request.getDescricao());
        produto.setPreco(request.getPreco());
        produto.setCategoria(request.getCategoria());
        produto.setImagem(request.getImagem());

        produtoRepository.atualizar(produto);
        return produto;
    }

    public void deletarProduto(String id) {
        Produto produto = buscarProdutoPorId(id);
        produtoRepository.deletar(produto.getId());
    }
}
