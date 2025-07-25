package com.ecomerccer.loja.controller;

import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.repository.ProdutoRepository;
import com.ecomerccer.loja.service.UpdateProduto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class UpadateController {

    private final ProdutoRepository produtoRepository;
    private  final UpdateProduto updateProduto;

    public UpadateController(ProdutoRepository produtoRepository, UpdateProduto updateProduto) {
        this.produtoRepository = produtoRepository;
        this.updateProduto = updateProduto;
    }

    @PutMapping("/up/{idproduto}")
    public ResponseEntity<?> atualizaProduto(@PathVariable("idproduto") Long  idproduto, @RequestBody Produto produto){
        Produto produto1 = updateProduto.update(idproduto, produto);

        if(produto1 != null){
            return new ResponseEntity(produto1, HttpStatus.OK);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    }

