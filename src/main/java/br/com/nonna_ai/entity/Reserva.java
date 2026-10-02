package br.com.nonna_ai.entity;

import java.time.LocalDateTime;

public class Reserva {
    private String id;
    private String idCliente;
    private LocalDateTime horario;
    private Integer quantidadeDePessoas;
    private String tipoEvento;
    private String motivoCancelamento;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public LocalDateTime getHorario() { return horario; }
    public void setHorario(LocalDateTime horario) { this.horario = horario; }

    public Integer getQuantidadeDePessoas() { return quantidadeDePessoas; }
    public void setQuantidadeDePessoas(Integer quantidadeDePessoas) { this.quantidadeDePessoas = quantidadeDePessoas; }

    public String getTipoEvento() { return tipoEvento; }
    public void setTipoEvento(String tipoEvento) { this.tipoEvento = tipoEvento; }

    public String getMotivoCancelamento() { return motivoCancelamento; }
    public void setMotivoCancelamento(String motivoCancelamento) { this.motivoCancelamento = motivoCancelamento; }
}
