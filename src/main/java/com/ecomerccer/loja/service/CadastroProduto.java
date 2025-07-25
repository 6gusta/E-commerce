package com.ecomerccer.loja.service;

import com.ecomerccer.loja.model.Categoria;
import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.model.Tamanho;
import com.ecomerccer.loja.model.TipoProduto;
import com.ecomerccer.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CadastroProduto {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto cadastrarprodutos(
            String nomeProduto,
            String descricaoProduto,
            BigDecimal precoUnitario,
            Categoria categoriaProduto,
            Boolean estoqueProduto,
            LocalDate dataCadastro,
            String imagemProduto,
            List<Tamanho> tamanhosDisponiveis, // ✅ AGORA LIST
            int quantidade,
            BigDecimal valorPromocional,
            TipoProduto tipo
    ) {
        Produto produto = new Produto();

        produto.setNomeProduto(nomeProduto);
        produto.setDescProduto(descricaoProduto);
        produto.setPrecoProduto(precoUnitario);
        produto.setCategoriaProduto(categoriaProduto);
        produto.setTipo(tipo);
        produto.setEstoqueProduto(estoqueProduto);
        produto.setDataCadastro(dataCadastro);
        produto.setImagemProduto(imagemProduto);
        produto.setTamanhosDisponiveis(tamanhosDisponiveis); // ✅ AGORA FUNCIONA
        produto.setQuantidade(quantidade);
        produto.setValorPromocional(valorPromocional);

        return produtoRepository.save(produto);
    }
}
