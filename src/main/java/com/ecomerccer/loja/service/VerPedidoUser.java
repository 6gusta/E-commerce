package com.ecomerccer.loja.service;

import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.repository.IntemPedidoRepository;
import org.springframework.stereotype.Service;


@Service
public class VerPedidoUser {

    private final IntemPedidoRepository intemPedidoRepository;

    public VerPedidoUser(IntemPedidoRepository intemPedidoRepository) {
        this.intemPedidoRepository = intemPedidoRepository;
    }

    public IntemPedido VerPedidoUsuario(Long idIntemPedido) {
        return intemPedidoRepository.findById(idIntemPedido).orElse(null);
    }

}