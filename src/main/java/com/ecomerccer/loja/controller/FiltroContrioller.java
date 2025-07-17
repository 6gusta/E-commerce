package com.ecomerccer.loja.controller;


import com.ecomerccer.loja.model.Categoria;
import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.model.Tamanho;
import com.ecomerccer.loja.model.TipoProduto;
import com.ecomerccer.loja.service.FiltraProduto;
import org.hibernate.engine.jdbc.mutation.TableInclusionChecker;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/filtro")
@CrossOrigin(origins = "*")
public class FiltroContrioller {

    private final FiltraProduto  filtraProduto;

    public FiltroContrioller(FiltraProduto filtraProduto) {
        this.filtraProduto = filtraProduto;
    }

    @GetMapping("/filtrar")
    public List<Produto> filtraproduto(
            @RequestParam(required = false)TipoProduto produto,
            @RequestParam(required = false) Tamanho tamanho,
            @RequestParam(required = false)BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax
            ){
        return filtraProduto.filtraProduto(produto, tamanho, precoMin, precoMax);
    }
}
