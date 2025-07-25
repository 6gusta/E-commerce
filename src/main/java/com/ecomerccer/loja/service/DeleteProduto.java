package com.ecomerccer.loja.service;

import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DeleteProduto {

    private final ProdutoRepository produtoRepository;

    private static final Logger LOGGER = Logger.getLogger(DeleteProduto.class.getName());

    public DeleteProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public boolean DelProduto(Long idProduto){

        Produto  produto = produtoRepository.findById(idProduto) .orElseThrow(() -> new IllegalArgumentException("Produto não econtrado : " + idProduto));
         produtoRepository.delete(produto);

        LOGGER.warning("Deletado com sucesso");

        return true;
    }
}
