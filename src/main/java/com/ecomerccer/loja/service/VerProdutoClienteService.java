package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.repository.IntemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerProdutoClienteService {

    private final IntemPedidoRepository intemPedidoRepository;

    public VerProdutoClienteService(IntemPedidoRepository intemPedidoRepository) {
        this.intemPedidoRepository = intemPedidoRepository;
    }

    public List<IntemPedido> listarTodosPedidos() {
        return intemPedidoRepository.findAll();
    }

}
