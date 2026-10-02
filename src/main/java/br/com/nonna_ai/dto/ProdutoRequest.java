package br.com.nonna_ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProdutoRequest {
    @NotBlank(message = "NOME DO PRODUTO É OBRIGATÓRIO")
    private String nome;
    
    private String descricao;
    
    @NotNull(message = "PREÇO DO PRODUTO É OBRIGATÓRIO")
    @Positive(message = "PREÇO DO PRODUTO DEVE SER MAIOR QUE ZERO")
    private Double preco;
    
    @NotBlank(message = "CATEGORIA DO PRODUTO É OBRIGATÓRIA")
    private String categoria;
    
    private String imagem;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }
}
