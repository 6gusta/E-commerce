package com.ecomerccer.loja.controller;


import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.model.TipoProduto;
import com.ecomerccer.loja.service.BuscaProduto;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/buscar")
public class BuscaController {

    private final BuscaProduto buscaProduto;

    public BuscaController(BuscaProduto buscaProduto) {
        this.buscaProduto = buscaProduto;
    }

    @GetMapping("/intem")
    public List<Produto> buscaPorNome(@RequestParam String termo){
        return buscaProduto.buscarPorNomeAproximado(termo);
    }



}
