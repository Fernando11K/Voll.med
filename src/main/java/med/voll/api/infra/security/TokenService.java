package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.auth0.jwt.interfaces.JWTVerifier;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.securirity.token.secret}")
    private String secret;
    private static final String ISSUER = "API Voll.med";
    public String gerarToken(Usuario usuario){
        System.out.println(secret);
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getLogin())
                    //.withClaim("id", usuario.getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }
    public String getSubject(String tokenJWT){

        DecodedJWT decodedJWT;
        try {

            Algorithm algoritmo = Algorithm.HMAC256(secret);
            JWTVerifier verificador = JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build();
            return verificador.verify(tokenJWT)
                              .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }
    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
