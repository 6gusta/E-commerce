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
    public ResponseEntity<InfoCliente> cadastracliente(@RequestBody InfoCliente infoCliente) {
        // Salva o cliente e retorna o objeto completo com ID
        InfoCliente clienteSalvo = infoClienteService.salvarCliente(infoCliente);
        return ResponseEntity.ok(clienteSalvo);
    }
}
