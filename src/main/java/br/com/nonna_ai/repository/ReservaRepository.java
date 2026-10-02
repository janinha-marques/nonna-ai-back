package br.com.nonna_ai.repository;

import br.com.nonna_ai.entity.Reserva;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReservaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvar(Reserva reserva) {
        String sql = "INSERT INTO Reserva (ID, IDCliente, Horario, Quantidade_de_pessoas, Tipo_Evento, Motivo_cancelamento) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, reserva.getId(), reserva.getIdCliente(), reserva.getHorario(),
                reserva.getQuantidadeDePessoas(), reserva.getTipoEvento(), reserva.getMotivoCancelamento());
    }

    public Optional<Reserva> buscarPorId(String id) {
        String sql = "SELECT ID, IDCliente, Horario, Quantidade_de_pessoas AS quantidadeDePessoas, Tipo_Evento AS tipoEvento, Motivo_cancelamento AS motivoCancelamento FROM Reserva WHERE ID = ?";
        List<Reserva> reservas = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Reserva.class), id);
        return reservas.stream().findFirst();
    }

    public void cancelar(String id, String motivoCancelamento) {
        String sql = "UPDATE Reserva SET Motivo_cancelamento = ? WHERE ID = ?";
        jdbcTemplate.update(sql, motivoCancelamento, id);
    }
}
