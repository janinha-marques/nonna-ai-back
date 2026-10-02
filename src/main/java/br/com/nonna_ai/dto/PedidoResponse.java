package br.com.nonna_ai.dto;

import br.com.nonna_ai.entity.Pedido;
import br.com.nonna_ai.entity.ProdutoPedido;

import java.util.List;

public class PedidoResponse {
    private Pedido pedido;
    private List<ProdutoPedido> itens;

    public PedidoResponse(Pedido pedido, List<ProdutoPedido> itens) {
        this.pedido = pedido;
        this.itens = itens;
    }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public List<ProdutoPedido> getItens() { return itens; }
    public void setItens(List<ProdutoPedido> itens) { this.itens = itens; }
}
