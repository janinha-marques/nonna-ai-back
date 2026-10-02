package br.com.nonna_ai.controller;

import br.com.nonna_ai.dto.PedidoCancelamentoRequest;
import br.com.nonna_ai.dto.PedidoRequest;
import br.com.nonna_ai.dto.PedidoResponse;
import br.com.nonna_ai.dto.PedidoUpdateRequest;
import br.com.nonna_ai.entity.Pedido;
import br.com.nonna_ai.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/pedidos")
    public ResponseEntity<PedidoResponse> criarPedido(@Valid @RequestBody PedidoRequest request) {
        return new ResponseEntity<>(pedidoService.criarPedido(request), HttpStatus.CREATED);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> listarPedidos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        if (size > 100) size = 100;
        return ResponseEntity.ok(pedidoService.buscarPedidosNaoConcluidos(page, size));
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<PedidoResponse> buscarPedido(@PathVariable String id) {
        return ResponseEntity.ok(pedidoService.buscarPedidoDetalhado(id));
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> atualizarPedido(
            @PathVariable String id,
            @RequestBody PedidoUpdateRequest request) {
        return ResponseEntity.ok(pedidoService.atualizarPedido(id, request));
    }

    @PostMapping("/pedidos/{id}/cancelar")
    public ResponseEntity<Void> cancelarPedido(
            @PathVariable String id,
            @Valid @RequestBody PedidoCancelamentoRequest request) {
        pedidoService.cancelarPedido(id, request);
        return ResponseEntity.noContent().build();
    }
}
