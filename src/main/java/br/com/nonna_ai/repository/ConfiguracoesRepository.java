package br.com.nonna_ai.repository;

import br.com.nonna_ai.entity.Configuracoes;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ConfiguracoesRepository {

    private final JdbcTemplate jdbcTemplate;

    public ConfiguracoesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvar(Configuracoes config) {
        jdbcTemplate.update("DELETE FROM Configuracoes");
        jdbcTemplate.update("INSERT INTO Configuracoes (Horario_funcionamento) VALUES (?)", config.getHorarioFuncionamento());
    }

    public Optional<Configuracoes> buscar() {
        String sql = "SELECT Horario_funcionamento AS horarioFuncionamento FROM Configuracoes LIMIT 1";
        List<Configuracoes> configs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Configuracoes.class));
        return configs.stream().findFirst();
    }
}
