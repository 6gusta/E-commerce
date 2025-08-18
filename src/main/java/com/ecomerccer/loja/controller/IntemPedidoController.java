package com.ecomerccer.loja.controller;


import com.ecomerccer.loja.model.InfoCliente;
import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.service.InfoClienteService;
import com.ecomerccer.loja.service.SelectProduto;
import com.ecomerccer.loja.service.VerPedidoUser;
import com.ecomerccer.loja.service.VerProdutoClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/intempedido")
@CrossOrigin(origins = "*")
public class IntemPedidoController {

    private final SelectProduto produtos;
    private final VerPedidoUser  verPedidoUser;
    private final VerProdutoClienteService verProdutoClienteService;
    private final InfoClienteService infoClienteService;


    @Autowired
    public IntemPedidoController(SelectProduto produtos, VerPedidoUser verPedidoUser, VerProdutoClienteService verProdutoClienteService, InfoClienteService infoClienteService) {
        this.produtos = produtos;
        this.verPedidoUser = verPedidoUser;
        this.verProdutoClienteService = verProdutoClienteService;
        this.infoClienteService = infoClienteService;
    }

    @PostMapping("/enviapedidos")
    public ResponseEntity<?> intemselecionado(@RequestBody IntemPedido intemPedido) {

        InfoCliente clienteSalvo;

        // Verifica se o objeto cliente veio no JSON
        if (intemPedido.getCliente() == null) {
            return ResponseEntity.badRequest().body("Cliente não informado");
        }

        // Ajuste: usar Long e checar null
        Long idCliente = intemPedido.getCliente().getIdCliente();

        if (idCliente != null && idCliente > 0) {
            // Cliente já existe, busca no banco
            clienteSalvo = infoClienteService.buscarPorId(idCliente);
            if (clienteSalvo == null) {
                return ResponseEntity.badRequest().body("Cliente não encontrado");
            }
        } else {
            // Cliente novo, salva
            clienteSalvo = infoClienteService.salvarCliente(intemPedido.getCliente());
        }

        // Cria e salva o pedido
        IntemPedido pedidoSalvo = produtos.produtoSelecionado(
                intemPedido.getNomeProduto(),
                intemPedido.getCategoriaProduto(),
                intemPedido.getPrecoUnitario(),
                intemPedido.getDescricaoProduto(),
                intemPedido.getTamanhosDisponiveis(),
                intemPedido.getQuantidadeItemCliente(),
                intemPedido.getTipoPagamento(),
                clienteSalvo
        );

        return ResponseEntity.ok(Map.of(
                "mensagem", "Pedido Enviado com sucesso",
                "idPedido", pedidoSalvo.getIdIntemPedido()
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

    @GetMapping("/listarpedidos")
    public ResponseEntity<List<IntemPedido>> listarTodosPedidos() {
        List<IntemPedido> pedidos = verProdutoClienteService.listarTodosPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{idIntemPedido}/cliente/{idCliente}")
    public ResponseEntity<?> vincularClienteAoPedido(
            @PathVariable Long idIntemPedido,
            @PathVariable Long idCliente) {

        IntemPedido pedido = verPedidoUser.VerPedidoUsuario(idIntemPedido);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        InfoCliente cliente = infoClienteService.buscarPorId(idCliente);
        if (cliente == null) {
            return ResponseEntity.badRequest().body("Cliente não encontrado");
        }

        pedido.setCliente(cliente);
        produtos.salvarPedido(pedido); // Confirme que esse método salva o pedido no banco

        return ResponseEntity.ok("Cliente vinculado ao pedido com sucesso");
    }




}
