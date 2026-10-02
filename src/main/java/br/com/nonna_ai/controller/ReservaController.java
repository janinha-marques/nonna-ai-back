package br.com.nonna_ai.controller;

import br.com.nonna_ai.dto.ReservaCancelamentoRequest;
import br.com.nonna_ai.dto.ReservaRequest;
import br.com.nonna_ai.entity.Reserva;
import br.com.nonna_ai.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/reserva")
    public ResponseEntity<Reserva> criarReserva(@Valid @RequestBody ReservaRequest request) {
        return new ResponseEntity<>(reservaService.criarReserva(request), HttpStatus.CREATED);
    }

    @PostMapping("/reserva/{id}/cancelar")
    public ResponseEntity<Void> cancelarReserva(
            @PathVariable String id,
            @Valid @RequestBody ReservaCancelamentoRequest request) {
        reservaService.cancelarReserva(id, request);
        return ResponseEntity.noContent().build();
    }
}
