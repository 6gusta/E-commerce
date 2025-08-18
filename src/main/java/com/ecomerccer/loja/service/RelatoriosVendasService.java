package com.ecomerccer.loja.service;


import com.ecomerccer.loja.repository.IntemPedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RelatoriosVendasService {

    private final IntemPedidoRepository intemPedidoRepository;

    public RelatoriosVendasService(IntemPedidoRepository intemPedidoRepository) {
        this.intemPedidoRepository = intemPedidoRepository;
    }

    public BigDecimal totalHoje(){
        return intemPedidoRepository.getTotalVendasHoje();
    }

    public BigDecimal totalSemana(){
        return intemPedidoRepository.getTotalVendasSemana();
    }

    public BigDecimal totalMes(){
        return intemPedidoRepository.getTotalVendasMes();
    }

    public BigDecimal totalAno(){
        return intemPedidoRepository.getTotalVendasAno();
    }
}
