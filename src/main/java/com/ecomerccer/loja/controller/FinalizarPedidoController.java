package com.ecomerccer.loja.controller;


import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.model.TipoPagamento;
import com.ecomerccer.loja.service.FinalizarPedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/finalizar")
@CrossOrigin(origins = "*")
public class FinalizarPedidoController {

    private final FinalizarPedidoService finalizarPedidoService;

    public FinalizarPedidoController(FinalizarPedidoService finalizarPedidoService) {
        this.finalizarPedidoService = finalizarPedidoService;
    }

    @PutMapping("/pedido/{idIntemPedido}")
    public ResponseEntity<IntemPedido> finalizarPedido(@PathVariable long idIntemPedido,@RequestParam TipoPagamento tipoPagamento){

        IntemPedido atualizdo = finalizarPedidoService.atualizaformadepagamento(idIntemPedido,tipoPagamento);
        return  ResponseEntity.ok(atualizdo);

    }
}
