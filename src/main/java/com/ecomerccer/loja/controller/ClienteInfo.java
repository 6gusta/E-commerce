package com.ecomerccer.loja.controller;

import com.ecomerccer.loja.model.InfoCliente;
import com.ecomerccer.loja.service.InfoClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteInfo {

    private final InfoClienteService infoClienteService;

    public ClienteInfo(InfoClienteService infoClienteService) {
        this.infoClienteService = infoClienteService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastracliente(@RequestBody InfoCliente infoCliente) {

        infoClienteService.dadosCliente(
                infoCliente.getNome(),
                infoCliente.getTelefone(),
                infoCliente.getEmail(),
                infoCliente.getEndereco(),
                infoCliente.getCidade(),
                infoCliente.getEstado(),
                infoCliente.getCep(),
                infoCliente.getComplemento()
        );

        return ResponseEntity.ok(" Infomaçoes do Cliente cadastrado com sucesso");

    }

}
