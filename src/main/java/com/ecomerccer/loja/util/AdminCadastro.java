package com.ecomerccer.loja.util;


import com.ecomerccer.loja.model.CadastroAdmin;
import com.ecomerccer.loja.repository.CadastroAdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component

public class AdminCadastro {

    private final CadastroAdminRepository cadastroAdminRepository;
    private final PasswordEncoder encoder;

    public AdminCadastro(CadastroAdminRepository cadastroAdminRepository, PasswordEncoder encoder) {
        this.cadastroAdminRepository = cadastroAdminRepository;
        this.encoder = encoder;
    }

    @PostConstruct
public  void cadastro(){
        CadastroAdmin  cadastroAdmin = new CadastroAdmin();
         String nome = "admin";
        if (cadastroAdminRepository.existsCadastroAdminByNome(nome)){
            System.out.println("Administrador padrão já existe.");


        }
        cadastroAdmin.setNome(nome);
        cadastroAdmin.setSenha(encoder.encode("admin"));
        cadastroAdmin.setRole("ADMIN");
        cadastroAdminRepository.save(cadastroAdmin);
        System.out.println("Administrador padrão criado com sucesso!");


    }


}
