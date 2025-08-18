package com.ecomerccer.loja.controller;


import com.ecomerccer.loja.service.RelatoriosVendasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/relatorio")
public class RelatioVendasController {
    private final RelatoriosVendasService relatoriosVendasService;

    public RelatioVendasController(RelatoriosVendasService relatoriosVendasService) {
        this.relatoriosVendasService = relatoriosVendasService;
    }

    @GetMapping("/hoje")
    public BigDecimal getHoje(){
        return relatoriosVendasService.totalHoje();
    }

    @GetMapping("/semana")
    public BigDecimal getTotalVendas(){

        return relatoriosVendasService.totalSemana();

    }
    @GetMapping("/mes")
    public BigDecimal getTotalMes(){
        return relatoriosVendasService.totalMes();
    }

    @GetMapping("/ano")
    public BigDecimal getTotalAno(){
        return relatoriosVendasService.totalAno();
    }

}
