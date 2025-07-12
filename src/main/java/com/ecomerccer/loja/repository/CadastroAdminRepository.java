package com.ecomerccer.loja.repository;

import com.ecomerccer.loja.model.CadastroAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadastroAdminRepository extends JpaRepository<CadastroAdmin,Long> {

    boolean existsCadastroAdminByNome(String nome);
    CadastroAdmin findByNome(String nome);
}
