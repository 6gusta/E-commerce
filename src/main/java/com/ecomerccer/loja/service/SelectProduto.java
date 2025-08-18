package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.*;
import com.ecomerccer.loja.repository.IntemPedidoRepository;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public IntemPedido produtoSelecionado(String nomeProduto, Categoria CategoriaProduto, BigDecimal precoUnitario,  String descricaoProduto, List tamanhosDisponiveis, int quantidadeintemCliente, TipoPagamento    tipoPagamento,  InfoCliente cliente){



        IntemPedido intemPedido = new IntemPedido();

        intemPedido.setNomeProduto(nomeProduto);
        intemPedido.setCategoriaProduto(CategoriaProduto);
        intemPedido.setPrecoUnitario(precoUnitario);
        intemPedido.setTamanhosDisponiveis(tamanhosDisponiveis);
        intemPedido.setQuantidadeItemCliente(quantidadeintemCliente);
        intemPedido.setDescricaoProduto(descricaoProduto);

        intemPedido.setTipoPagamento(tipoPagamento);

        BigDecimal total = produto.calcularTotal(precoUnitario, quantidadeintemCliente);
        intemPedido.setTotal(total);
        intemPedido.setDataPedido(LocalDateTime.now());
        intemPedido.setPedidoFinalizado(false);
        intemPedido.setCliente(cliente);


        //if(tipoPagamento ==  TipoPagamento.CARTAO) {


       // }else{

       // }//
        return intemPedidoRepository.save(intemPedido);







    }

    public IntemPedido salvarPedido(IntemPedido pedido) {
        return intemPedidoRepository.save(pedido);
    }

}
