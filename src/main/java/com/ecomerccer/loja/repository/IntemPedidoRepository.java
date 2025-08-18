package com.ecomerccer.loja.repository;

import com.ecomerccer.loja.model.IntemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface IntemPedidoRepository  extends JpaRepository<IntemPedido,Long> {

    @Query("SELECT SUM(ip.total) FROM IntemPedido ip WHERE ip.dataPedido = CURRENT_DATE")
    BigDecimal getTotalVendasHoje();





    @Query("SELECT SUM(i.total) FROM IntemPedido i WHERE FUNCTION('WEEK', i.dataPedido) = FUNCTION('WEEK', CURRENT_DATE) AND FUNCTION('YEAR', i.dataPedido) = FUNCTION('YEAR', CURRENT_DATE) AND i.pedidoFinalizado = true")
    BigDecimal getTotalVendasSemana();

    @Query("SELECT SUM(i.total) FROM IntemPedido i WHERE MONTH(i.dataPedido) = MONTH(CURRENT_DATE) AND YEAR(i.dataPedido) = YEAR(CURRENT_DATE) AND i.pedidoFinalizado = true")
    BigDecimal getTotalVendasMes();

    @Query("SELECT SUM(i.total) FROM IntemPedido i WHERE YEAR(i.dataPedido) = YEAR(CURRENT_DATE) AND i.pedidoFinalizado = true")
    BigDecimal getTotalVendasAno();


}
