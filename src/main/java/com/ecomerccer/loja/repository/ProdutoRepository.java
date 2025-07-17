package com.ecomerccer.loja.repository;

import com.ecomerccer.loja.model.Categoria;
import com.ecomerccer.loja.model.Produto;
import com.ecomerccer.loja.model.Tamanho;
import com.ecomerccer.loja.model.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository  extends JpaRepository<Produto,Long> {

    Produto findByNomeProduto(String nomeProduto);


    List<Produto> findByCategoriaProduto(Categoria categoriaProduto);

    @Query("""
        SELECT p FROM Produto p
        WHERE (:tipo IS NULL OR p.tipo = :tipo)
            AND (:tamanho IS NULL OR :tamanho MEMBER OF p.tamanhosDisponiveis)
          AND (:precoMin IS NULL OR p.precoProduto >= :precoMin)
          AND (:precoMax IS NULL OR p.precoProduto <= :precoMax)
    """)
    List<Produto> filtrarProdutos(
            @Param("tipo") TipoProduto produto,
            @Param("tamanho") Tamanho tamanho,
            @Param("precoMin") BigDecimal precoMin,
            @Param("precoMax") BigDecimal precoMax
    );
}
