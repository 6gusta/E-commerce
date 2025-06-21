package com.ecomerccer.loja.service;

import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerProduto {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto>listarProdutos(){
        return produtoRepository.findAll();
    }
}
