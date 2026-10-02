package br.com.nonna_ai.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class ReservaRequest {
    @NotBlank(message = "ID DO CLIENTE É OBRIGATÓRIO")
    private String idCliente;

    @NotNull(message = "HORÁRIO DA RESERVA É OBRIGATÓRIO")
    @Future(message = "O HORÁRIO DA RESERVA DEVE SER NO FUTURO")
    private LocalDateTime horario;

    @NotNull(message = "A QUANTIDADE DE PESSOAS É OBRIGATÓRIA")
    @Positive(message = "A QUANTIDADE DE PESSOAS DEVE SER MAIOR QUE ZERO")
    private Integer quantidadeDePessoas;

    private String tipoEvento;

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public LocalDateTime getHorario() { return horario; }
    public void setHorario(LocalDateTime horario) { this.horario = horario; }

    public Integer getQuantidadeDePessoas() { return quantidadeDePessoas; }
    public void setQuantidadeDePessoas(Integer quantidadeDePessoas) { this.quantidadeDePessoas = quantidadeDePessoas; }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }
}
