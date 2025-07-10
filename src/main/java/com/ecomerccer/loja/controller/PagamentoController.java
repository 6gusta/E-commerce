package com.ecomerccer.loja.controller;

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

    @PostMapping("/pagamentocartao")
    public ResponseEntity<Map<String, Object>> criaCheckout() {
        Map<String, Object> retorno = new HashMap<>();

        try {
            List<Object> lista = new ArrayList<>();
            Map<String, Object> item = new HashMap<>();
            Map<String, Object> priceData = new HashMap<>();
            Map<String, Object> productData = new HashMap<>();

            productData.put("name", "Produto Teste");

            priceData.put("currency", "brl");
            priceData.put("unit_amount", 5000); // R$ 50,00
            priceData.put("product_data", productData);

            item.put("price_data", priceData);
            item.put("quantity", 1);
            lista.add(item);

            Map<String, Object> params = new HashMap<>();
            params.put("payment_method_types", List.of("card"));
            params.put("line_items", lista);
            params.put("mode", "payment");
            params.put("success_url", "http://localhost:4200/sucesso");
            params.put("cancel_url", "http://localhost:4200/erro");

            Session session = Session.create(params);

            retorno.put("id", session.getId());
            retorno.put("url", session.getUrl());

            return ResponseEntity.ok(retorno);

        } catch (Exception e) {
            retorno.put("erro", e.getMessage());
            return ResponseEntity.status(500).body(retorno);
        }
    }
}
