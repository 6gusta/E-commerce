package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.model.Tamanho;
import com.ecomerccer.loja.model.TipoProduto;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FiltraProduto {

    private final ProdutoRepository produtoRepository;

    public FiltraProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> filtraProduto(TipoProduto produto, Tamanho tamanho, BigDecimal precoMin, BigDecimal precoMax){

        return produtoRepository.filtrarProdutos(produto, tamanho, precoMin, precoMax);

    }
}
