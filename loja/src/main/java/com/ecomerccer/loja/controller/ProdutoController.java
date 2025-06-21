package com.ecomerccer.loja.controller;


import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.service.CadastroProduto;
import com.ecomerccer.loja.service.VerProduto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final CadastroProduto cadastroProduto;
    private final VerProduto produto;

    public ProdutoController(CadastroProduto cadastroProduto, VerProduto produto) {
        this.cadastroProduto = cadastroProduto;
        this.produto = produto;
    }

    @PostMapping("/Register")
    public ResponseEntity<String> cadastrarProduto(@RequestBody Produto produto){
        System.out.println(" recebendo produtos "+ produto.toString());

        Produto produtos = cadastroProduto.cadastrarprodutos(
                produto.getNomeProduto(),
                produto.getDescProduto(),
                produto.getPrecoProduto(),
                produto.getCategoriaProduto(),
                produto.getEstoqueProduto(),
                produto.getDataCadastro(),
                produto.getImagemProduto()



        );

        return ResponseEntity.ok(" produto cadastrado com sucesso ");
    }

    @GetMapping("/verproduto")
     public ResponseEntity<List<Produto>> listarProdutos(){

            List<Produto> produtos = produto.listarProdutos();
            return ResponseEntity.ok(produtos);
    }


}
