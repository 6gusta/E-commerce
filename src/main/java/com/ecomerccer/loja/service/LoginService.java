package com.ecomerccer.loja.service;


import com.ecomerccer.loja.Exceptions.PermissaoInvalidaException;
import com.ecomerccer.loja.model.CadastroAdmin;
import com.ecomerccer.loja.model.LoginDTO;
import com.ecomerccer.loja.repository.CadastroAdminRepository;
import com.ecomerccer.loja.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LoginService {

    private final CadastroAdminRepository cadastroAdminRepository;
    private final PasswordEncoder passwordEncoder;
    private static Logger LOGGER = Logger.getLogger(LoginService.class.getName());
    private final  JwtUtil jwtUtil;

    public LoginService(CadastroAdminRepository cadastroAdminRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.cadastroAdminRepository = cadastroAdminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String autenticar(LoginDTO loginDTO){
        try {
        CadastroAdmin cadastroAdmin = cadastroAdminRepository.findByNome(loginDTO.getNome());

        if(cadastroAdmin == null){
            throw new PermissaoInvalidaException("Permissão inválida para este usuário");

        }
        if(!passwordEncoder.matches(loginDTO.getSenha(), cadastroAdmin.getSenha())){
            throw new IllegalArgumentException("senha incorreta");
        }

        String role = cadastroAdmin.getRole();
        if(role == null || role.isBlank()){

            throw new IllegalArgumentException("Usuario nao possui perfil definido");


        }

        if(role == null || !role.equalsIgnoreCase("ADMIN")){
            throw new IllegalArgumentException("Permissão inválida para este usuário");

        }


        String token = jwtUtil.generateToken(cadastroAdmin.getNome(), role);
        LOGGER.info("Login Bem sucedido" + cadastroAdmin.getNome());
        LOGGER.info("Token autenticado: " + token);

        return token;
    }catch (Exception e) {
            // Loga a stacktrace no console
            e.printStackTrace();
            throw e;
        }
}}
