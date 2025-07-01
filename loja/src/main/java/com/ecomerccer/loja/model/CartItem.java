package com.ecomerccer.loja.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cartintem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idcart;

    @ManyToOne
    @JoinColumn(name = "idproduto")
    private Produto produto;
    private String tamanhoEscolhido;
    private int quantidade;

    // Getters e Setters

    public Long getIdcart() {
        return idcart;
    }

    public void setIdcart(Long idcart) {
        this.idcart = idcart;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
