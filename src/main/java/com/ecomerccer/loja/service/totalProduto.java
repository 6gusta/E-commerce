package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.repository.IntemPedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class totalProduto {

    private final IntemPedidoRepository intemPedidoRepository;

    public totalProduto(IntemPedidoRepository intemPedidoRepository) {
        this.intemPedidoRepository = intemPedidoRepository;
    }

    public BigDecimal calcularTotal(BigDecimal precoUnitario, int quantidade) {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
