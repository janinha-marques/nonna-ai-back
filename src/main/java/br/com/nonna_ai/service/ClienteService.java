package br.com.nonna_ai.service;

import br.com.nonna_ai.dto.ClienteRequest;
import br.com.nonna_ai.entity.Cliente;
import br.com.nonna_ai.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> buscarClientes(int page, int size) {
        int offset = page * size;
        return clienteRepository.buscarTodos(size, offset);
    }

    public Cliente buscarClientePorId(String id) {
        return clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("CLIENTE NÃO ENCONTRADO"));
    }

    public Cliente atualizarCliente(String id, ClienteRequest request) {
        Cliente cliente = buscarClientePorId(id);
        
        cliente.setNome(request.getNome());
        cliente.setSobrenome(request.getSobrenome());
        cliente.setEmail(request.getEmail());
        cliente.setSenha(request.getSenha());
        
        clienteRepository.atualizar(cliente);
        return cliente;
    }
}
