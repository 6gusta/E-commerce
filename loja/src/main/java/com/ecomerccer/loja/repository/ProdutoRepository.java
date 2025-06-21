package com.ecomerccer.loja.repository;

import com.ecomerccer.loja.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto,Long> {

    Produto findByNomeProduto(String nomeProduto);

}
