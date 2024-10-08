package com.ti.interdisciplinar242.Configurations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ti.interdisciplinar242.Models.TesteModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
 /*   @Value("${api.security.token.secret")
    private String secret;

    public String geradorToken(TesteModel testeModel){
        try{
            Algorithm algorithm = Algorithm.HMAC256();

            String token = JWT.create()
                    .withIssuer("login-usuario")
                    .withSubject(testeModel.getLogin())
                    .withExpiresAt()
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro na autenticação");
        }
    }

    public String validacaoToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256();
            return JWT.require(algorithm)
                    .withIssuer("login-usuario")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return null;
        }
    }

    private Instant geradorDataExpira(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
*/
}
