package com.ecomerccer.loja.repository;

import com.ecomerccer.loja.model.Categoria;
import com.ecomerccer.loja.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository  extends JpaRepository<Produto,Long> {

    Produto findByNomeProduto(String nomeProduto);


    List<Produto> findByCategoriaProduto(Categoria categoriaProduto);
}
