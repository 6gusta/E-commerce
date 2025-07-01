package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.CartItem;
import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.repository.CartIntemRepository;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class CartIntemService {

    private final CartIntemRepository cartIntemRepository;
    private final ProdutoRepository produtoRepository;

    public CartIntemService(CartIntemRepository cartIntemRepository, ProdutoRepository produtoRepository) {
        this.cartIntemRepository = cartIntemRepository;
        this.produtoRepository = produtoRepository;
    }

    public CartItem adicionaIntem(CartItem cartItem){
        Long idproduto = cartItem.getProduto().getIdproduto();

        if(idproduto == null || idproduto <= 0){

            throw  new RuntimeException("valido  id do produto invalido " + idproduto );
        }

        Produto produtoExistente = produtoRepository.findById(idproduto)
                .orElseThrow(() -> new RuntimeException("Produto com ID " + idproduto + " não encontrado."));
        cartItem.setProduto(produtoExistente);
        return  cartIntemRepository.save(cartItem);
    }

    public List<CartItem> listaIntem(){
        return cartIntemRepository.findAll();
    }

    public void removerIntem(Long idcart){
        cartIntemRepository.deleteById(idcart);
    }

    public double CalcularTotal() {
        return cartIntemRepository.findAll().stream()
                .mapToDouble(intem -> intem.getProduto().getPrecoProduto().doubleValue() * intem.getQuantidade())
                .sum();
    }



}

