package com.ecomerccer.loja.controller;

import com.ecomerccer.loja.Exceptions.PermissaoInvalidaException;
import com.ecomerccer.loja.model.CadastroAdmin;
import com.ecomerccer.loja.model.LoginDTO;
import com.ecomerccer.loja.service.LoginService;
import com.ecomerccer.loja.service.verAdmin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
class LoginController {

    private final LoginService loginService;
    private  final verAdmin verAdmin;

    public LoginController(LoginService loginService, verAdmin verAdmin) {
        this.loginService = loginService;
        this.verAdmin = verAdmin;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            String token = loginService.autenticar(loginDTO);
            return ResponseEntity.ok(token);

        } catch (PermissaoInvalidaException.UsuarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");

        } catch (PermissaoInvalidaException.SenhaIncorretaException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");

        } catch (PermissaoInvalidaException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Permissão inválida para este usuário");

        } catch (Exception e) {
            e.printStackTrace(); // Para depuração
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }




}
