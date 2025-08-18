package com.ecomerccer.loja.controller;

import com.ecomerccer.loja.model.PedidoClienteDTO;
import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.service.CriarPedidoComClientess;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientepedido")
public class ClientePedidoController {

    private final CriarPedidoComClientess criarPedidoComClientess;

    public ClientePedidoController(CriarPedidoComClientess criarPedidoComClientess) {
        this.criarPedidoComClientess = criarPedidoComClientess;
    }

    @PostMapping("/pedido-completo")
    public ResponseEntity<PedidoClienteDTO> criarPedidoComCliente(@RequestBody PedidoClienteDTO dto) {
        System.out.println("Quantidade recebida: " + dto.getQuantidadeItemCliente());
        // Cria a entidade IntemPedido preenchendo todos os campos
        IntemPedido pedido = criarPedidoComClientess.criarPedidoComCliente(dto);

        // Preenche explicitamente a quantidadeItemCliente
        pedido.setQuantidadeItemCliente(dto.getQuantidadeItemCliente());

        // Monta o DTO de retorno
        PedidoClienteDTO retorno = new PedidoClienteDTO();
        retorno.setNome(pedido.getCliente().getNome());
        retorno.setTelefone(pedido.getCliente().getTelefone());
        retorno.setEmail(pedido.getCliente().getEmail());
        retorno.setEndereco(pedido.getCliente().getEndereco());
        retorno.setCidade(pedido.getCliente().getCidade());
        retorno.setEstado(pedido.getCliente().getEstado());
        retorno.setCep(pedido.getCliente().getCep());
        retorno.setComplemento(pedido.getCliente().getComplemento());

        retorno.setNomeProduto(pedido.getNomeProduto());
        retorno.setCategoriaProduto(pedido.getCategoriaProduto().name());
        retorno.setPrecoUnitario(pedido.getPrecoUnitario());
        retorno.setQuantidadeItemCliente(pedido.getQuantidadeItemCliente()); // <- Aqui vai o valor certo
        retorno.setDescricaoProduto(pedido.getDescricaoProduto());
        retorno.setTamanhosDisponiveis(pedido.getTamanhosDisponiveis());
        retorno.setTipoPagamento(pedido.getTipoPagamento().name());

        return ResponseEntity.ok(retorno);
    }
}
