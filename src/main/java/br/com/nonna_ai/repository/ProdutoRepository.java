package br.com.nonna_ai.repository;

import br.com.nonna_ai.entity.Produto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvar(Produto produto) {
        String sql = "INSERT INTO Produtos (ID, Nome, Descricao, Preco, Categoria, Imagem) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, produto.getId(), produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getCategoria(), produto.getImagem());
    }

    public List<Produto> buscarTodos(int limit, int offset) {
        String sql = "SELECT * FROM Produtos LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Produto.class), limit, offset);
    }

    public Optional<Produto> buscarPorId(String id) {
        String sql = "SELECT * FROM Produtos WHERE ID = ?";
        List<Produto> produtos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Produto.class), id);
        return produtos.stream().findFirst();
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE Produtos SET Nome = ?, Descricao = ?, Preco = ?, Categoria = ?, Imagem = ? WHERE ID = ?";
        jdbcTemplate.update(sql, produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getCategoria(), produto.getImagem(), produto.getId());
    }

    public void deletar(String id) {
        String sql = "DELETE FROM Produtos WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
