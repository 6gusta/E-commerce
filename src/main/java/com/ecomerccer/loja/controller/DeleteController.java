package com.ecomerccer.loja.controller;


import com.ecomerccer.loja.repository.ProdutoRepository;
import com.ecomerccer.loja.service.DeleteProduto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class DeleteController {

    private final ProdutoRepository produtoRepository;
    private final DeleteProduto deleteProduto;

    public DeleteController(ProdutoRepository produtoRepository, DeleteProduto deleteProduto) {
        this.produtoRepository = produtoRepository;
        this.deleteProduto = deleteProduto;
    }

    @DeleteMapping("/intem/{idproduto}")
    public ResponseEntity<?>excluir(@PathVariable("idproduto") long idproduto){
        boolean sucesso = deleteProduto.DelProduto(idproduto);

        if(sucesso){
            return ResponseEntity.ok(Map.of("produto deletado com sucesso", true));
        }else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", "PRODUTO NÃO ENCONTRADO"));
        }
    }
}
