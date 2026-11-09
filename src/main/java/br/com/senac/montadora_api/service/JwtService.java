package br.com.senac.montadora_api.service;

import br.com.senac.montadora_api.entidades.Usuarios;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {
    private String segredo = "cvHWa40A18vP7TvwCr7EvdWW60yidXPgpXVWslDK04X";

    public String gerarToken(Usuarios usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            return JWT.create().withIssuer("montadora-api").withSubject(usuario.getEmail())
                    .withExpiresAt(this.gerarDataExpiracao()).sign(algorithm);
        }catch (JWTCreationException e){
            throw e;
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            return JWT.require(algorithm).withIssuer("montadora-api").
                    build().verify(token).getSubject();
        }catch (JWTVerificationException e){
            throw null;
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
