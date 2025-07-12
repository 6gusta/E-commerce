package com.ecomerccer.loja.securaty;

import com.ecomerccer.loja.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                Claims claims = jwtUtil.extrairtoken(token);

                if (claims == null) {
                    throw new Exception("Token inválido ou expirado");
                }

                String nome = claims.get("nome", String.class);
                String role = claims.get("role", String.class);

                if (nome == null || role == null) {
                    throw new Exception("Token sem informações suficientes");
                }

                // ❗️🚫 Aqui é onde você barra quem não for ADMIN
                if (!"ADMIN".equalsIgnoreCase(role)) {
                    throw new Exception("Acesso negado: apenas ADMIN pode acessar esta área");
                }

                List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(nome, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Usuário autenticado: " + nome + " - Role: " + role);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Erro ao processar o token: " + e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}