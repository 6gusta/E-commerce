package com.ecomerccer.loja.service;

import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CadastroProduto {

    @Autowired
    private ProdutoRepository produtoRepository;
    public Produto cadastrarprodutos(String nomeProduto, String descricaoProduto, BigDecimal precoUnitario, String CategoriaProduto, Boolean estoqueProduto, LocalDate dataCadastro, String imagemProduto) {

        Produto produto = new Produto();

        produto.setNomeProduto(nomeProduto);
        produto.setDescProduto( descricaoProduto);
        produto.setPrecoProduto( precoUnitario);
        produto.setCategoriaProduto(CategoriaProduto);
        produto.setEstoqueProduto(estoqueProduto);
        produto.setDataCadastro(dataCadastro);
        produto.setImagemProduto(imagemProduto);




        return produtoRepository.save(produto);



    }
}
