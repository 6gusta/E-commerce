package com.ecomerccer.loja.controller;

import com.ecomerccer.loja.model.IntemPedido;
import com.ecomerccer.loja.service.SelectProduto;
import com.ecomerccer.loja.service.VerPedidoUser;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/metodo")
@CrossOrigin( origins = "*")
public class PagamentoController {

    static {
        Stripe.apiKey = "sk_test_51RiRDOQLNMAVnpmccFjpwh87RkYuPaOG27wqJgQTJEtmQjfQ4ft1nQca4qN5xtlyb5GDmS3agcpPezBvOO1SRLYv00WsvzT1TZ";
    }

    String codigo = "DU" + UUID.randomUUID().toString().substring(0,8).toUpperCase() + "BR";


    private final SelectProduto produto;
    private final VerPedidoUser verPedidoUser;

    public PagamentoController(SelectProduto produto, VerPedidoUser verPedidoUser) {
        this.produto = produto;
        this.verPedidoUser = verPedidoUser;
    }


    @PostMapping("/pagamentocartao")
    public ResponseEntity<Map<String, Object>> criaCheckout(@RequestBody Map<String, Object> dados) {
        Map<String, Object> retorno = new HashMap<>();

        try {

            Long idPedido = Long.valueOf(dados.get("idPedido").toString());

            IntemPedido intemPedido = verPedidoUser.VerPedidoUsuario(idPedido);

            if (intemPedido == null){
                retorno.put(" erro", " pedido não econtrado");
                return ResponseEntity.status(404).body(retorno);
            }

            List<Object> lista = new ArrayList<>();
            Map<String, Object> item = new HashMap<>();
            Map<String, Object> priceData = new HashMap<>();
            Map<String, Object> productData = new HashMap<>();

            productData.put("name", intemPedido.getNomeProduto());

            priceData.put("currency", "brl");
            long valorcentavos = Math.round(intemPedido.getPrecoUnitario().doubleValue() * 100);
            priceData.put("unit_amount", valorcentavos); // R$ 50,00
            priceData.put("product_data", productData);

            item.put("price_data", priceData);
            item.put("quantity", intemPedido.getQuantidadeItemCliente());
            lista.add(item);

            Map<String, Object> params = new HashMap<>();
            params.put("payment_method_types", List.of("card"));
            params.put("line_items", lista);
            params.put("mode", "payment");
            params.put("success_url", "http://localhost:4200/sucesso");
            params.put("cancel_url", "http://localhost:4200/erro");
            params.put("client_reference_id", idPedido.toString());

            Session session = Session.create(params);

            retorno.put("id", session.getId());
            retorno.put("url", session.getUrl());
            retorno.put("codigoRastreamento", codigo);


            return ResponseEntity.ok(retorno);

        } catch (Exception e) {
            retorno.put("erro", e.getMessage());
            return ResponseEntity.status(500).body(retorno);
        }
    }
}
