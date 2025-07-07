package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.Categoria;
import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.repository.IntemPedidoRepository;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SelectProduto {

    private final ProdutoRepository produtoRepository;
    private final IntemPedidoRepository intemPedidoRepository;

    private final totalProduto produto;

    public SelectProduto(ProdutoRepository produtoRepository, IntemPedidoRepository intemPedidoRepository, totalProduto produto) {
        this.produtoRepository = produtoRepository;
        this.intemPedidoRepository = intemPedidoRepository;
        this.produto = produto;
    }

    public IntemPedido produtoSelecionado(String nomeProduto, Categoria CategoriaProduto, BigDecimal precoUnitario, int quantidade, String descricaoProduto, List tamanhosDisponiveis, int quantidadeintemCliente){


           if (quantidade > quantidadeintemCliente){
               throw new IllegalArgumentException(" valor acima do que tem no estoque ");
           }
        IntemPedido intemPedido = new IntemPedido();
        intemPedido.setNomeProduto(nomeProduto);
        intemPedido.setCategoriaProduto(CategoriaProduto);
        intemPedido.setPrecoUnitario(precoUnitario);
        intemPedido.setTamanhosDisponiveis(tamanhosDisponiveis);
        intemPedido.setQuantidadeintemCliente(quantidadeintemCliente);
        intemPedido.setDescricaoProduto(descricaoProduto);
        intemPedido.setQuantidade(quantidade);

        BigDecimal total = produto.calcularTotal(precoUnitario, quantidadeintemCliente);
        intemPedido.setTotal(total);


        return intemPedidoRepository.save(intemPedido);







    }
}
