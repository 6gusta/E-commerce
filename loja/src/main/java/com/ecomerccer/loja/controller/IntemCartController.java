package com.ecomerccer.loja.controller;

import com.ecomerccer.loja.model.CartItem;
import com.ecomerccer.loja.service.CartIntemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class IntemCartController {

    private final CartIntemService cartIntemService;


    public IntemCartController(CartIntemService cartIntemService) {
        this.cartIntemService = cartIntemService;
    }

    @PostMapping("/adicionar")
    public CartItem adcionar(@RequestBody CartItem cartItem){
        return cartIntemService.adicionaIntem(cartItem);
    }

    @GetMapping("/listacart")
    public List<CartItem> listaCart(){

        return cartIntemService.listaIntem();

    }

    @DeleteMapping("/{idcart}")
    public void deletarCart(@PathVariable Long idcart){

       cartIntemService.removerIntem(idcart);

    }

    @GetMapping("/total")
    public double calcularTotal(){
        return  cartIntemService.CalcularTotal();
    }

}
