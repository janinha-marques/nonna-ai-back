package br.com.nonna_ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PedidoRequest {
    @NotBlank(message = "ID DO CLIENTE É OBRIGATÓRIO")
    private String idCliente;

    @NotBlank(message = "TIPO DE ENTREGA É OBRIGATÓRIO")
    private String tipoEntrega;

    private String endereco;

    @NotBlank(message = "FORMA DE PAGAMENTO É OBRIGATÓRIA")
    private String formaDePagamento;

    @NotBlank(message = "TELEFONE É OBRIGATÓRIO")
    private String telefone;

    @NotNull(message = "PRODUTOS SÃO OBRIGATÓRIOS")
    private List<String> produtosIds;

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getTipoEntrega() { return tipoEntrega; }
    public void setTipoEntrega(String tipoEntrega) { this.tipoEntrega = tipoEntrega; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getFormaDePagamento() { return formaDePagamento; }
    public void setFormaDePagamento(String formaDePagamento) { this.formaDePagamento = formaDePagamento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public List<String> getProdutosIds() { return produtosIds; }
    public void setProdutosIds(List<String> produtosIds) { this.produtosIds = produtosIds; }
}
