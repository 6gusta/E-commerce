package com.ecomerccer.loja.model;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class IntemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idIntemPedido;
    private String nomeProduto;

    @Enumerated(EnumType.STRING)
    private Categoria CategoriaProduto;

    private BigDecimal precoUnitario;




    private String descricaoProduto;

    @ElementCollection
    private List<String> tamanhosDisponiveis;
    private Integer quantidadeItemCliente;

   private BigDecimal total;
    private LocalDateTime dataPedido;

    private boolean pedidoFinalizado;
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    @ManyToOne
    @JoinColumn(name = "cliente_id_cliente")
    private InfoCliente cliente;



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





    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public boolean isPedidoFinalizado() {
        return pedidoFinalizado;
    }

    public void setPedidoFinalizado(boolean pedidoFinalizado) {
        this.pedidoFinalizado = pedidoFinalizado;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public InfoCliente getCliente() {
        return cliente;
    }

    public void setCliente(InfoCliente cliente) {
        this.cliente = cliente;
    }

    public Integer getQuantidadeItemCliente() {
        return quantidadeItemCliente;
    }

    public void setQuantidadeItemCliente(Integer quantidadeItemCliente) {
        this.quantidadeItemCliente = quantidadeItemCliente;
    }

    public long getIdIntemPedido() {
        return idIntemPedido;
    }

    public void setIdIntemPedido(long idIntemPedido) {
        this.idIntemPedido = idIntemPedido;
    }
}
