package gerencia.reservas.api.infra.security;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import gerencia.reservas.api.entities.usuario.Usuario;

@Service
public class TokenService {

	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("API GerenciaProposta").withSubject(usuario.getLogin())
					.withClaim("id", usuario.getId()).withExpiresAt(dataExpiracao()).sign(algoritmo);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar o token JWT", exception);
		}
	}

	public String getSubject(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo).withIssuer("API GerenciaProposta").build().verify(tokenJWT).getSubject();
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("token JWT inválido ou expirado", exception);
		}
	}
	
	public String getId(String tokenJWT) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo).withIssuer("API GerenciaProposta").build().verify(tokenJWT).getClaim("id").toString();
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("token JWT inválido ou expirado", exception);
		}
	}
	
	public String getIdUsuarioHeader(HttpHeaders headers) {
		try {
			var authorization = headers.getFirst(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
			System.out.println("*jwt token: " + authorization);
			String idUsuario = this.getId(authorization);
			return idUsuario;
		} catch (JWTVerificationException exception) {
			throw new RuntimeException("token JWT inválido ou expirado", exception);
		}
	}
	
	

	private Instant dataExpiracao() {
		// TODO Auto-generated method stub
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}