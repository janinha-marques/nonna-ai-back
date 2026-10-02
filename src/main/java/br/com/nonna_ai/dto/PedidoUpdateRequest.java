package br.com.nonna_ai.dto;

import java.time.LocalDateTime;

public class PedidoUpdateRequest {
    private String status;
    private LocalDateTime horarioSaida;
    private LocalDateTime horarioFinalizacao;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getHorarioSaida() { return horarioSaida; }
    public void setHorarioSaida(LocalDateTime horarioSaida) { this.horarioSaida = horarioSaida; }

    public LocalDateTime getHorarioFinalizacao() { return horarioFinalizacao; }
    public void setHorarioFinalizacao(LocalDateTime horarioFinalizacao) { this.horarioFinalizacao = horarioFinalizacao; }
}
