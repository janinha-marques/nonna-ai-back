package br.com.nonna_ai.service;

import br.com.nonna_ai.dto.ReservaCancelamentoRequest;
import br.com.nonna_ai.dto.ReservaRequest;
import br.com.nonna_ai.entity.Reserva;
import br.com.nonna_ai.repository.ClienteRepository;
import br.com.nonna_ai.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;

    public ReservaService(ReservaRepository reservaRepository, ClienteRepository clienteRepository) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
    }

    public Reserva criarReserva(ReservaRequest request) {
        if (clienteRepository.buscarPorId(request.getIdCliente()).isEmpty()) {
            throw new IllegalArgumentException("CLIENTE NÃO ENCONTRADO");
        }

        Reserva reserva = new Reserva();
        reserva.setId(UUID.randomUUID().toString());
        reserva.setIdCliente(request.getIdCliente());
        reserva.setHorario(request.getHorario());
        reserva.setQuantidadeDePessoas(request.getQuantidadeDePessoas());
        reserva.setTipoEvento(request.getTipoEvento());

        reservaRepository.salvar(reserva);
        return reserva;
    }

    public void cancelarReserva(String id, ReservaCancelamentoRequest request) {
        if (reservaRepository.buscarPorId(id).isEmpty()) {
            throw new IllegalArgumentException("RESERVA NÃO ENCONTRADA");
        }
        reservaRepository.cancelar(id, request.getMotivoCancelamento());
    }
}
