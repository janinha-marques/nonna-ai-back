package br.com.nonna_ai.repository;

import br.com.nonna_ai.entity.Cliente;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Cliente> buscarTodos(int limit, int offset) {
        String sql = "SELECT * FROM Cliente LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cliente.class), limit, offset);
    }

    public Optional<Cliente> buscarPorId(String id) {
        String sql = "SELECT * FROM Cliente WHERE ID = ?";
        List<Cliente> clientes = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cliente.class), id);
        return clientes.stream().findFirst();
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE Cliente SET Nome = ?, Sobrenome = ?, email = ?, senha = ? WHERE ID = ?";
        jdbcTemplate.update(sql, cliente.getNome(), cliente.getSobrenome(),
                cliente.getEmail(), cliente.getSenha(), cliente.getId());
    }
}
