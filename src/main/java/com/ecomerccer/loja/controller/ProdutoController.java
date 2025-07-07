package com.ecomerccer.loja.controller;

import com.ecomerccer.loja.model.Categoria;
import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.service.CadastroProduto;
import com.ecomerccer.loja.service.VerProduto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final CadastroProduto cadastroProduto;
    private final VerProduto produtoService;
    private final VerProduto verProduto;

    public ProdutoController(CadastroProduto cadastroProduto, VerProduto produtoService, VerProduto verProduto) {
        this.cadastroProduto = cadastroProduto;
        this.produtoService = produtoService;
        this.verProduto = verProduto;
    }

    @PostMapping("/register")
    public ResponseEntity<String> cadastrarProduto(@RequestBody Produto produto) {
        System.out.println("Recebendo produto: " + produto);

        cadastroProduto.cadastrarprodutos(
                produto.getNomeProduto(),
                produto.getDescProduto(),
                produto.getPrecoProduto(),
                produto.getCategoriaProduto(),
                produto.getEstoqueProduto(),
                produto.getDataCadastro(),
                produto.getImagemProduto(),
                produto.getTamanhosDisponiveis(),
                produto.getQuantidade(),
                produto.getValorPromocional()
        );

        return ResponseEntity.ok("Produto cadastrado com sucesso");
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Produto>> listaPorcategoria(@PathVariable String categoria) {
        Categoria categoria1 = Categoria.valueOf(categoria.toUpperCase());

        return ResponseEntity.ok(verProduto.listaPorCategoriaProduto(categoria1));
    }

    @GetMapping("/verproduto")
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/detalhes/{idproduto}")
    public ResponseEntity<Produto> buscaProdutoPorId(@PathVariable("idproduto") Long idproduto) {
        Optional<Produto> produtoOpt = produtoService.buscarPorId(idproduto);

        return produtoOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
