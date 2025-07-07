package com.ecomerccer.loja.service;

import com.ecomerccer.loja.model.Categoria;
import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VerProduto {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto>listarProdutos(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long idproduto) {
        return produtoRepository.findById(idproduto);
    }


    public List<Produto>  listaPorCategoriaProduto(Categoria categoriaProduto){

        return produtoRepository.findByCategoriaProduto(categoriaProduto);

    }
}
