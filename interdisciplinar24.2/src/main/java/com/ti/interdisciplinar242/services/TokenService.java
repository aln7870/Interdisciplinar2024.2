package com.ti.interdisciplinar242.services;

import org.springframework.stereotype.Service;

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
