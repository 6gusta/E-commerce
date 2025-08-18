package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.InfoCliente;
import com.ecomerccer.loja.repository.InfoClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class InfoClienteService {

    private final InfoClienteRepository infoClienteRepository;

    public InfoClienteService(InfoClienteRepository infoClienteRepository) {
        this.infoClienteRepository = infoClienteRepository;
    }

    /** Salva o cliente e retorna o objeto completo com ID gerado */
    public InfoCliente salvarCliente(InfoCliente infoCliente) {
        return infoClienteRepository.save(infoCliente);
    }

    /** Busca cliente pelo ID */
    public InfoCliente buscarPorId(Long idCliente) {
        return infoClienteRepository.findById(idCliente).orElse(null);
    }
}

