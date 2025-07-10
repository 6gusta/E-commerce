package com.ecomerccer.loja.repository;


import com.ecomerccer.loja.model.InfoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoClienteRepository extends JpaRepository<InfoCliente,Long> {
}
