package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.CadastroAdmin;
import com.ecomerccer.loja.repository.CadastroAdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class verAdmin {

    private final CadastroAdminRepository cadastroAdminRepository;

    public verAdmin(CadastroAdminRepository cadastroAdminRepository) {
        this.cadastroAdminRepository = cadastroAdminRepository;
    }

    public Optional<CadastroAdmin> verAdmidados(long idadmin) {
        return cadastroAdminRepository.findById(idadmin);
    }

}
