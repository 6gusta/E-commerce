package com.ecomerccer.loja.service;


import com.ecomerccer.loja.model.InfoCliente;
import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.model.TipoPagamento;
import com.ecomerccer.loja.repository.InfoClienteRepository;
import com.ecomerccer.loja.repository.IntemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinalizarPedidoService {

    private final IntemPedidoRepository intemPedidoRepository;
    private final PedidoRabbitProducer pedidoRabbitProducer;
    private final EmailService emailService;
    private final InfoClienteRepository infoClienteRepository;

    public FinalizarPedidoService(IntemPedidoRepository intemPedidoRepository, PedidoRabbitProducer pedidoRabbitProducer, EmailService emailService, InfoClienteRepository infoClienteRepository) {
        this.intemPedidoRepository = intemPedidoRepository;
        this.pedidoRabbitProducer = pedidoRabbitProducer;
        this.emailService = emailService;
        this.infoClienteRepository = infoClienteRepository;
    }

    public IntemPedido atualizaformadepagamento(Long idPedido, TipoPagamento novotipoPagamento) {
        IntemPedido pedido = intemPedidoRepository.findById(idPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + idPedido));

        pedido.setTipoPagamento(novotipoPagamento);
        pedido.setPedidoFinalizado(true);

        IntemPedido pedidoSalvo = intemPedidoRepository.save(pedido);

        emailService.enviaemail(
                "luiz.carvalho7110@sounidesc.com.br",
                "Pedido finalizado",
                "O pedido " + idPedido + " foi finalizado. e ja esta pronto pra envio!"
        );

        InfoCliente cliente = pedido.getCliente();

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado para o pedido " + idPedido);
        }

        emailService.enviaemail(
                cliente.getEmail(),
                "Seu pedido foi finalizado",
                "Seu pedido " + idPedido + " foi finalizado com sucesso. agurade para mais informaçoes"
        );

        pedidoRabbitProducer.enviamsg("fila.pedido.finalizado", pedidoSalvo);

        return pedidoSalvo;
    }}