package br.com.nonna_ai.controller;

import br.com.nonna_ai.dto.ConfiguracoesRequest;
import br.com.nonna_ai.entity.Configuracoes;
import br.com.nonna_ai.service.ConfiguracoesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfiguracoesController {

    private final ConfiguracoesService configuracoesService;

    public ConfiguracoesController(ConfiguracoesService configuracoesService) {
        this.configuracoesService = configuracoesService;
    }

    @PostMapping(value = {"/configuracoes", "/configurações"})
    public ResponseEntity<Configuracoes> atualizarConfiguracoes(@Valid @RequestBody ConfiguracoesRequest request) {
        return ResponseEntity.ok(configuracoesService.atualizarConfiguracoes(request));
    }

    @GetMapping(value = {"/configuracoes", "/configurações"})
    public ResponseEntity<Configuracoes> buscarConfiguracoes() {
        return ResponseEntity.ok(configuracoesService.buscarConfiguracoes());
    }
}
