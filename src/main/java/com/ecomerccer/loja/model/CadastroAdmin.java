package com.ecomerccer.loja.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CadastroAdmin {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long idadmin;
    private String nome;
    private String senha;
    private String role;

    public long getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(long idadmin) {
        this.idadmin = idadmin;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
