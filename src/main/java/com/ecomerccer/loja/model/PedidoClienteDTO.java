package com.ecomerccer.loja.model;

import java.math.BigDecimal;
import java.util.List;

public class PedidoClienteDTO {

    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;

    private String nomeProduto;
    private String categoriaProduto;
    private BigDecimal precoUnitario;
    private Integer quantidadeItemCliente;

    private String descricaoProduto;
    private List<String> tamanhosDisponiveis;
    private String tipoPagamento;

    // Construtor vazio (Spring/Jackson precisa dele)
    public PedidoClienteDTO() {}

    // Construtor completo
    public PedidoClienteDTO(String nome, String telefone, String email, String endereco,
                            String cidade, String estado, String cep, String complemento,
                            String nomeProduto, String categoriaProduto, BigDecimal precoUnitario,
                            Integer quantidadeItemCliente, String descricaoProduto,
                            List<String> tamanhosDisponiveis, String tipoPagamento) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.precoUnitario = precoUnitario;
        this.quantidadeItemCliente = quantidadeItemCliente;
        this.descricaoProduto = descricaoProduto;
        this.tamanhosDisponiveis = tamanhosDisponiveis;
        this.tipoPagamento = tipoPagamento;
    }

    // Getters e setters (iguais aos que você já tinha)
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getComplemento() { return complemento; }
    public void setComplemento(String complemento) { this.complemento = complemento; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public String getCategoriaProduto() { return categoriaProduto; }
    public void setCategoriaProduto(String categoriaProduto) { this.categoriaProduto = categoriaProduto; }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }

    public Integer getQuantidadeItemCliente() { return quantidadeItemCliente; }
    public void setQuantidadeItemCliente(Integer quantidadeItemCliente) { this.quantidadeItemCliente = quantidadeItemCliente; }

    public String getDescricaoProduto() { return descricaoProduto; }
    public void setDescricaoProduto(String descricaoProduto) { this.descricaoProduto = descricaoProduto; }

    public List<String> getTamanhosDisponiveis() { return tamanhosDisponiveis; }
    public void setTamanhosDisponiveis(List<String> tamanhosDisponiveis) { this.tamanhosDisponiveis = tamanhosDisponiveis; }

    public String getTipoPagamento() { return tipoPagamento; }
    public void setTipoPagamento(String tipoPagamento) { this.tipoPagamento = tipoPagamento; }
}
