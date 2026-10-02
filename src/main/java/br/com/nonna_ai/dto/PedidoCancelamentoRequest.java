package br.com.nonna_ai.dto;

import jakarta.validation.constraints.NotBlank;

public class PedidoCancelamentoRequest {
    @NotBlank(message = "MOTIVO DO CANCELAMENTO É OBRIGATÓRIO")
    private String motivoCancelamento;

    public String getMotivoCancelamento() { return motivoCancelamento; }
    public void setMotivoCancelamento(String motivoCancelamento) { this.motivoCancelamento = motivoCancelamento; }
}
