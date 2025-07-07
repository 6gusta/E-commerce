package com.ecomerccer.loja.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class IntemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idIntemPedido;
    private String nomeProduto;
    private Categoria CategoriaProduto;
    private BigDecimal precoUnitario;
    private int quantidade;
    private String descricaoProduto;
    @ElementCollection
    private List<String> tamanhosDisponiveis;
   private  int quantidadeintemCliente;

   private BigDecimal total;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Categoria getCategoriaProduto() {
        return CategoriaProduto;
    }

    public void setCategoriaProduto(Categoria categoriaProduto) {
        CategoriaProduto = categoriaProduto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public List getTamanhosDisponiveis() {
        return tamanhosDisponiveis;
    }

    public void setTamanhosDisponiveis(List tamanhosDisponiveis) {
        this.tamanhosDisponiveis = tamanhosDisponiveis;
    }

    public int getQuantidadeintemCliente() {
        return quantidadeintemCliente;
    }

    public void setQuantidadeintemCliente(int quantidadeintemCliente) {
        this.quantidadeintemCliente = quantidadeintemCliente;
    }

    public long getIdIntemPedido() {
        return idIntemPedido;
    }

    public void setIdIntemPedido(long idIntemPedido) {
        this.idIntemPedido = idIntemPedido;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
