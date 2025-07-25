package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProduto {

    private final ProdutoRepository produtoRepository;

    public UpdateProduto(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto update(Long idProduto, Produto produto) {
        Optional<Produto> opt = produtoRepository.findById(idProduto);

        if (opt.isPresent()) {
            Produto produto1 = opt.get();
            produto1.setNomeProduto(produto.getNomeProduto());
            produto1.setPrecoProduto(produto.getPrecoProduto());
            produto1.setQuantidade(produto.getQuantidade());
            produto1.setDescProduto(produto.getDescProduto());
            produto1.setCategoriaProduto(produto.getCategoriaProduto());
            produto1.setTipo(produto.getTipo());
            produto1.setEstoqueProduto(produto.getEstoqueProduto());
            produto1.setDataCadastro(produto.getDataCadastro());
            produto1.setImagemProduto(produto.getImagemProduto());
            produto1.setTamanhosDisponiveis(produto.getTamanhosDisponiveis());
            produto1.setValorPromocional(produto.getValorPromocional());

            return produtoRepository.save(produto1);
        }

        // Retorno alternativo caso o produto não exista
        // Pode lançar uma exceção, retornar null, ou usar Optional.
        throw new RuntimeException("Produto com ID " + idProduto + " não encontrado.");
    }
}
