package com.ecomerccer.loja.repository;

import com.ecomerccer.loja.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartIntemRepository  extends JpaRepository<CartItem, Long> {
}
