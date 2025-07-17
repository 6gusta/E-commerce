package com.ecomerccer.loja.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idproduto;

    private String nomeProduto;
    private String descProduto;
    private BigDecimal precoProduto;

    @Enumerated(EnumType.STRING)
    private Categoria categoriaProduto;
    @Enumerated(EnumType.STRING)
    private TipoProduto tipo;



    private Boolean estoqueProduto;
    private LocalDate dataCadastro;

    @Lob
    private String imagemProduto;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Tamanho> tamanhosDisponiveis;

    private int quantidade;

    private BigDecimal valorPromocional;


    // Getters
    public long getIdproduto() {
        return idproduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getDescProduto() {
        return descProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public Categoria getCategoriaProduto() {
        return categoriaProduto;
    }

    public Boolean getEstoqueProduto() {
        return estoqueProduto;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public String getImagemProduto() {
        return imagemProduto;
    }

    // Setters
    public void setIdproduto(long idproduto) {
        this.idproduto = idproduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setDescProduto(String descProduto) {
        this.descProduto = descProduto;
    }

    public void setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
    }

    public void setCategoriaProduto(Categoria categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public void setEstoqueProduto(Boolean estoqueProduto) {
        this.estoqueProduto = estoqueProduto;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setImagemProduto(String imagemProduto) {
        this.imagemProduto = imagemProduto;
    }





    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorPromocional() {
        return valorPromocional;
    }

    public void setValorPromocional(BigDecimal valorPromocional) {
        this.valorPromocional = valorPromocional;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }


    public List<Tamanho> getTamanhosDisponiveis() {
        return tamanhosDisponiveis;
    }

    public void setTamanhosDisponiveis(List<Tamanho> tamanhosDisponiveis) {
        this.tamanhosDisponiveis = tamanhosDisponiveis;
    }
}
