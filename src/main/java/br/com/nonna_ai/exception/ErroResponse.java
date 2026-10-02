package br.com.nonna_ai.exception;

import java.util.List;

public class ErroResponse {
    private int status;
    private List<String> erros;
    private String horario;

    public ErroResponse(int status, List<String> erros, String horario) {
        this.status = status;
        this.erros = erros;
        this.horario = horario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
