package br.com.nonna_ai.service;

import br.com.nonna_ai.dto.ConfiguracoesRequest;
import br.com.nonna_ai.entity.Configuracoes;
import br.com.nonna_ai.repository.ConfiguracoesRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracoesService {

    private final ConfiguracoesRepository configuracoesRepository;

    public ConfiguracoesService(ConfiguracoesRepository configuracoesRepository) {
        this.configuracoesRepository = configuracoesRepository;
    }

    public Configuracoes atualizarConfiguracoes(ConfiguracoesRequest request) {
        Configuracoes config = new Configuracoes();
        config.setHorarioFuncionamento(request.getHorarioFuncionamento());
        configuracoesRepository.salvar(config);
        return config;
    }

    public Configuracoes buscarConfiguracoes() {
        return configuracoesRepository.buscar().orElse(new Configuracoes());
    }
}
