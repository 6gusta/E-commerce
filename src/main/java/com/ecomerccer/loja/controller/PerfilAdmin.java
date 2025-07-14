package com.ecomerccer.loja.controller;

import com.ecomerccer.loja.model.CadastroAdmin;
import com.ecomerccer.loja.service.verAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping ("/perfil")
public class PerfilAdmin {

    private final verAdmin  verAdmin;

    public PerfilAdmin(verAdmin verAdmin) {
        this.verAdmin = verAdmin;
    }

    @GetMapping("/veradmin/{idadmin}")
    public ResponseEntity<CadastroAdmin> veradmin(@PathVariable("idadmin") Long idadmin) {
        Optional<CadastroAdmin> cadastroAdmin = verAdmin.verAdmidados(idadmin);

        return cadastroAdmin
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
