package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BuscaProduto {

    private final ProdutoRepository produtoRepository;

    public BuscaProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    public List<Produto> buscarPorNomeAproximado(String termo) {
        String termoLower = termo.toLowerCase();

        // Filtrar produtos que contenham o termo (ignorando case)
        List<Produto> todos = produtoRepository.findAll();
        List<Produto> encontrados = new ArrayList<>();

        for (Produto produto : todos) {
            String nomeLower = produto.getNomeProduto().toLowerCase();

            if (nomeLower.contains(termoLower)) {
                encontrados.add(produto);
            }
        }

        return encontrados;
    }

}
