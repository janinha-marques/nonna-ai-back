package br.com.nonna_ai.dto;

import jakarta.validation.constraints.NotBlank;

public class ConfiguracoesRequest {
    @NotBlank(message = "HORÁRIO DE FUNCIONAMENTO É OBRIGATÓRIO")
    private String horarioFuncionamento;

    public String getHorarioFuncionamento() { return horarioFuncionamento; }
    public void setHorarioFuncionamento(String horarioFuncionamento) { this.horarioFuncionamento = horarioFuncionamento; }
}
