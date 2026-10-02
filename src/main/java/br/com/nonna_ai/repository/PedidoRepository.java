package br.com.nonna_ai.repository;

import br.com.nonna_ai.entity.Pedido;
import br.com.nonna_ai.entity.ProdutoPedido;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository {

    private final JdbcTemplate jdbcTemplate;

    public PedidoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void salvar(Pedido pedido, List<ProdutoPedido> itens) {
        String sqlPedido = "INSERT INTO Pedido (ID, IDCliente, PrecoTotal, TipoEntrega, Endereco, Forma_de_pagamento, HorarioCriacao, Telefone, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sqlPedido, pedido.getId(), pedido.getIdCliente(), pedido.getPrecoTotal(),
                pedido.getTipoEntrega(), pedido.getEndereco(), pedido.getFormaDePagamento(),
                pedido.getHorarioCriacao(), pedido.getTelefone(), pedido.getStatus());

        String sqlItem = "INSERT INTO Produtos_Pedido (ID, IDPedido, IDProduto, Preco) VALUES (?, ?, ?, ?)";
        for (ProdutoPedido item : itens) {
            jdbcTemplate.update(sqlItem, item.getId(), item.getIdPedido(), item.getIdProduto(), item.getPreco());
        }
    }

    public List<Pedido> buscarNaoConcluidos(int limit, int offset) {
        // Ordenado do mais atrasado e status mais antigo
        String sql = "SELECT * FROM Pedido WHERE Status != 'CONCLUÍDO' AND Status != 'CANCELADO' ORDER BY HorarioCriacao ASC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pedido.class), limit, offset);
    }

    public Optional<Pedido> buscarPorId(String id) {
        String sql = "SELECT ID, IDCliente, PrecoTotal, TipoEntrega, Endereco, Forma_de_pagamento AS formaDePagamento, HorarioCriacao, HorarioSaida, HorarioFinalizacao, Telefone, Status, Motivo_Cancelamento AS motivoCancelamento FROM Pedido WHERE ID = ?";
        List<Pedido> pedidos = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Pedido.class), id);
        return pedidos.stream().findFirst();
    }

    public List<ProdutoPedido> buscarItensPorPedidoId(String idPedido) {
        String sql = "SELECT ID, IDPedido, IDProduto, Preco FROM Produtos_Pedido WHERE IDPedido = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProdutoPedido.class), idPedido);
    }

    public void atualizarStatusEHorarios(Pedido pedido) {
        String sql = "UPDATE Pedido SET Status = ?, HorarioSaida = ?, HorarioFinalizacao = ? WHERE ID = ?";
        jdbcTemplate.update(sql, pedido.getStatus(), pedido.getHorarioSaida(), pedido.getHorarioFinalizacao(), pedido.getId());
    }

    public void cancelar(String id, String motivoCancelamento) {
        String sql = "UPDATE Pedido SET Status = 'CANCELADO', Motivo_Cancelamento = ? WHERE ID = ?";
        jdbcTemplate.update(sql, motivoCancelamento, id);
    }
}
