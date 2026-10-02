package br.com.nonna_ai.service;

import br.com.nonna_ai.dto.PedidoCancelamentoRequest;
import br.com.nonna_ai.dto.PedidoRequest;
import br.com.nonna_ai.dto.PedidoResponse;
import br.com.nonna_ai.dto.PedidoUpdateRequest;
import br.com.nonna_ai.entity.Pedido;
import br.com.nonna_ai.entity.Produto;
import br.com.nonna_ai.entity.ProdutoPedido;
import br.com.nonna_ai.repository.ClienteRepository;
import br.com.nonna_ai.repository.PedidoRepository;
import br.com.nonna_ai.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public PedidoResponse criarPedido(PedidoRequest request) {
        if (clienteRepository.buscarPorId(request.getIdCliente()).isEmpty()) {
            throw new IllegalArgumentException("CLIENTE NÃO ENCONTRADO");
        }

        Pedido pedido = new Pedido();
        pedido.setId(UUID.randomUUID().toString());
        pedido.setIdCliente(request.getIdCliente());
        pedido.setTipoEntrega(request.getTipoEntrega());
        pedido.setEndereco(request.getEndereco());
        pedido.setFormaDePagamento(request.getFormaDePagamento());
        pedido.setTelefone(request.getTelefone());
        pedido.setHorarioCriacao(LocalDateTime.now());
        pedido.setStatus("CRIADO");

        double precoTotal = 0.0;
        List<ProdutoPedido> itens = new ArrayList<>();

        for (String idProduto : request.getProdutosIds()) {
            Produto produto = produtoRepository.buscarPorId(idProduto)
                    .orElseThrow(() -> new IllegalArgumentException("PRODUTO NÃO ENCONTRADO"));
            
            ProdutoPedido item = new ProdutoPedido();
            item.setId(UUID.randomUUID().toString());
            item.setIdPedido(pedido.getId());
            item.setIdProduto(produto.getId());
            item.setPreco(produto.getPreco());
            
            precoTotal += produto.getPreco();
            itens.add(item);
        }

        pedido.setPrecoTotal(precoTotal);
        pedidoRepository.salvar(pedido, itens);

        return new PedidoResponse(pedido, itens);
    }

    public List<Pedido> buscarPedidosNaoConcluidos(int page, int size) {
        int offset = page * size;
        return pedidoRepository.buscarNaoConcluidos(size, offset);
    }

    public PedidoResponse buscarPedidoDetalhado(String id) {
        Pedido pedido = pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("PEDIDO NÃO ENCONTRADO"));
        List<ProdutoPedido> itens = pedidoRepository.buscarItensPorPedidoId(id);
        return new PedidoResponse(pedido, itens);
    }

    public Pedido atualizarPedido(String id, PedidoUpdateRequest request) {
        Pedido pedido = pedidoRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("PEDIDO NÃO ENCONTRADO"));
        
        if (request.getStatus() != null) pedido.setStatus(request.getStatus());
        if (request.getHorarioSaida() != null) pedido.setHorarioSaida(request.getHorarioSaida());
        if (request.getHorarioFinalizacao() != null) pedido.setHorarioFinalizacao(request.getHorarioFinalizacao());
        
        pedidoRepository.atualizarStatusEHorarios(pedido);
        return pedido;
    }

    public void cancelarPedido(String id, PedidoCancelamentoRequest request) {
        if (pedidoRepository.buscarPorId(id).isEmpty()) {
            throw new IllegalArgumentException("PEDIDO NÃO ENCONTRADO");
        }
        pedidoRepository.cancelar(id, request.getMotivoCancelamento());
    }
}
