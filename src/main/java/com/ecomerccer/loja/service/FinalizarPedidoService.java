package com.ecomerccer.loja.service;

import com.ecomerccer.loja.model.InfoCliente;
import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.model.TipoPagamento;
import com.ecomerccer.loja.repository.InfoClienteRepository;
import com.ecomerccer.loja.repository.IntemPedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class FinalizarPedidoService {

    private final IntemPedidoRepository intemPedidoRepository;
    private final PedidoRabbitProducer pedidoRabbitProducer;
    private final EmailService emailService;
    private final InfoClienteRepository infoClienteRepository;

    public FinalizarPedidoService(
            IntemPedidoRepository intemPedidoRepository,
            PedidoRabbitProducer pedidoRabbitProducer,
            EmailService emailService,
            InfoClienteRepository infoClienteRepository
    ) {
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

        // Email para administrador
        String htmlAdmin = "<html>" +
                "<body>" +
                "<h2>Pedido Finalizado!</h2>" +
                "<p>O pedido <strong>" + idPedido + "</strong> foi finalizado e já está pronto para envio.</p>" +
                "<p>Detalhes do pedido:</p>" +
                "<ul>" +
                "<li>Cliente: " + (pedido.getCliente() != null ? pedido.getCliente().getNome() : "Não informado") + "</li>" +
                "<li>Intem Pedido: " + pedido.getNomeProduto()+ "</li>" +
                "<li>Quantidade: " + pedido.getQuantidadeItemCliente()+ "</li>" +
                "<li>Tamanho: " + pedido.getQuantidadeItemCliente()+ "</li>" +
                "<li>Tipo de Pagamento: " + pedido.getTipoPagamento() + "</li>" +
                "<li>Total: R$ " + pedido.getTotal() + "</li>" +
                "</ul>" +
                "</body>" +
                "</html>";

        emailService.enviaemail(
                "luiz.carvalho7110@sounidesc.com.br",
                "Pedido finalizado",
                htmlAdmin
        );

        InfoCliente cliente = pedido.getCliente();

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado para o pedido " + idPedido);
        }

        // Email para cliente
        String htmlCliente = "<html>" +
                "<body>" +
                "<h2>Seu pedido foi finalizado!</h2>" +
                "<p>Olá <strong>" + cliente.getNome() + "</strong>,</p>" +
                "<p>Seu pedido foi finalizado com sucesso. Aguarde mais informações sobre o envio.</p>" +
                "<p>Resumo do pedido:</p>" +
                "<ul>" +
                "<li>Intem Pedido: " + pedido.getNomeProduto()+ "</li>" +
                "<li>Quantidade: " + pedido.getQuantidadeItemCliente()+ "</li>" +
                "<li>Tamanho: " + pedido.getQuantidadeItemCliente()+ "</li>" +
                "<li>Tipo de Pagamento: " + pedido.getTipoPagamento() + "</li>" +
                "<li>Total: R$ " + pedido.getTotal() + "</li>" +
                "</ul>" +
                "<p>Obrigado por comprar conosco!</p>" +
                "</body>" +
                "</html>";

        emailService.enviaemail(
                cliente.getEmail(),
                "Seu pedido foi finalizado",
                htmlCliente
        );

        // Envia mensagem para RabbitMQ
        pedidoRabbitProducer.enviamsg("fila.pedido.finalizado", pedidoSalvo);

        return pedidoSalvo;
    }
}
