package br.com.senac.montadora_api.config;

import br.com.senac.montadora_api.entidades.Usuarios;
import br.com.senac.montadora_api.repositorios.UsuariosRepositorio;
import br.com.senac.montadora_api.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = this.recuperarToken(request);
        String login = jwtService.validarToken(token);

        if (login != null) {
            Optional<Usuarios> usuario = usuariosRepositorio.findByEmail(login);
            if (usuario.isEmpty()) {
                throw new RuntimeException("usuário não encontrado!");
            }
            UsernamePasswordAuthenticationToken autorizacao =
                    new UsernamePasswordAuthenticationToken(usuario.get(), null, null);
            SecurityContextHolder.getContext().setAuthentication(autorizacao);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null) return null;
        return header.replace("Bearer ", "");
    }
}
