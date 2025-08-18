package com.ecomerccer.loja.service;

import com.ecomerccer.loja.model.PedidoClienteDTO;
import com.ecomerccer.loja.model.Categoria;
import com.ecomerccer.loja.model.InfoCliente;
import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.model.TipoPagamento;
import com.ecomerccer.loja.repository.InfoClienteRepository;
import com.ecomerccer.loja.repository.IntemPedidoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CriarPedidoComClientess {

    private final IntemPedidoRepository intemPedidoRepository;
    private final InfoClienteRepository infoClienteRepository;

    public CriarPedidoComClientess(IntemPedidoRepository intemPedidoRepository,
                                   InfoClienteRepository infoClienteRepository) {
        this.intemPedidoRepository = intemPedidoRepository;
        this.infoClienteRepository = infoClienteRepository;
    }

    public IntemPedido criarPedidoComCliente(PedidoClienteDTO dto) {
        // Criar e salvar cliente
        InfoCliente cliente = new InfoCliente();
        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEmail(dto.getEmail());
        cliente.setEndereco(dto.getEndereco());
        cliente.setCidade(dto.getCidade());
        cliente.setEstado(dto.getEstado());
        cliente.setCep(dto.getCep());
        cliente.setComplemento(dto.getComplemento());
        cliente = infoClienteRepository.save(cliente);

        // Converter Strings para Enums
        Categoria categoriaProduto = Categoria.valueOf(dto.getCategoriaProduto());
        TipoPagamento tipoPagamento = TipoPagamento.valueOf(dto.getTipoPagamento());

        // Criar pedido
        IntemPedido pedido = new IntemPedido();
        pedido.setNomeProduto(dto.getNomeProduto());
        pedido.setCategoriaProduto(categoriaProduto);
        pedido.setPrecoUnitario(dto.getPrecoUnitario());
        pedido.setDescricaoProduto(dto.getDescricaoProduto());
        pedido.setTamanhosDisponiveis(dto.getTamanhosDisponiveis());

        // Garantir quantidade válida
        Integer quantidade = dto.getQuantidadeItemCliente() != null && dto.getQuantidadeItemCliente() > 0
                ? dto.getQuantidadeItemCliente() : 1;
        pedido.setQuantidadeItemCliente(quantidade);

        pedido.setTipoPagamento(tipoPagamento);

        // Calcular total
        if (dto.getPrecoUnitario() != null) {
            BigDecimal total = dto.getPrecoUnitario().multiply(BigDecimal.valueOf(quantidade));
            pedido.setTotal(total);
        } else {
            pedido.setTotal(BigDecimal.ZERO);
        }

        // Data e status
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setPedidoFinalizado(false);
        pedido.setCliente(cliente);

        // Salvar e retornar
        return intemPedidoRepository.save(pedido);
    }
}
