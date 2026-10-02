package br.com.nonna_ai.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClienteRequest {
    @NotBlank(message = "NOME É OBRIGATÓRIO")
    private String nome;

    @NotBlank(message = "SOBRENOME É OBRIGATÓRIO")
    private String sobrenome;

    @NotBlank(message = "EMAIL É OBRIGATÓRIO")
    @Email(message = "EMAIL INVÁLIDO")
    private String email;

    @NotBlank(message = "SENHA É OBRIGATÓRIA")
    @Size(min = 6, message = "SENHA DEVE TER NO MÍNIMO 6 CARACTERES")
    private String senha;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSobrenome() { return sobrenome; }
    public void setSobrenome(String sobrenome) { this.sobrenome = sobrenome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
