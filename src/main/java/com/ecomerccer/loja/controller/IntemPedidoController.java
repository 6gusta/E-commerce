package com.ecomerccer.loja.controller;


import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.service.SelectProduto;
import com.ecomerccer.loja.service.VerPedidoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/intempedido")
@CrossOrigin(origins = "*")
public class IntemPedidoController {

    private final SelectProduto produtos;
    private final VerPedidoUser  verPedidoUser;

    @Autowired
    public IntemPedidoController(SelectProduto produtos, VerPedidoUser verPedidoUser) {
        this.produtos = produtos;
        this.verPedidoUser = verPedidoUser;
    }

    @PostMapping("/enviapedidos")
    public ResponseEntity<?> intemselecionado(@RequestBody IntemPedido intemPedido) {
        // Salva o pedido no banco e retorna com ID preenchido
        IntemPedido pedidoSalvo = produtos.produtoSelecionado(
                intemPedido.getNomeProduto(),
                intemPedido.getCategoriaProduto(),
                intemPedido.getPrecoUnitario(),
                intemPedido.getQuantidade(),
                intemPedido.getDescricaoProduto(),
                intemPedido.getTamanhosDisponiveis(),
                intemPedido.getQuantidadeintemCliente()
        );

        // Monta um JSON com o id do pedido para enviar pro Angular
        return ResponseEntity.ok().body(Map.of(
                "mensagem", "Pedido Enviado com sucesso",
                "idPedido", pedidoSalvo.getIdIntemPedido() // ou getIdIntemPedido()
        ));
    }




    @GetMapping("/listarpedidos/{idIntemPedido}")
    public ResponseEntity<IntemPedido> mostrapedidouser(@PathVariable("idIntemPedido") Long idIntemPedido){
        IntemPedido intemPedido = verPedidoUser.VerPedidoUsuario(idIntemPedido);
        if (intemPedido != null) {
            return ResponseEntity.ok(intemPedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
