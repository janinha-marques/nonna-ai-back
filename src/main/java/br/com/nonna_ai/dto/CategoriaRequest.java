package br.com.nonna_ai.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank(message = "NOME DA CATEGORIA É OBRIGATÓRIO")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
