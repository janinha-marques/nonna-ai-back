package br.com.nonna_ai.repository;

import br.com.nonna_ai.entity.Categoria;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoriaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvar(Categoria categoria) {
        String sql = "INSERT INTO Categoria (ID, Nome) VALUES (?, ?)";
        jdbcTemplate.update(sql, categoria.getId(), categoria.getNome());
    }

    public List<Categoria> buscarTodos(int limit, int offset) {
        String sql = "SELECT * FROM Categoria LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categoria.class), limit, offset);
    }

    public Optional<Categoria> buscarPorId(String id) {
        String sql = "SELECT * FROM Categoria WHERE ID = ?";
        List<Categoria> categorias = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categoria.class), id);
        return categorias.stream().findFirst();
    }

    public void atualizar(Categoria categoria) {
        String sql = "UPDATE Categoria SET Nome = ? WHERE ID = ?";
        jdbcTemplate.update(sql, categoria.getNome(), categoria.getId());
    }

    public void deletar(String id) {
        String sql = "DELETE FROM Categoria WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }
}
