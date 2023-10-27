package gerencia.reservas.api.infra.exception;


import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException exception) {
		var erros = exception.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosValidacao::new).toList());
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity tratarSqlException(SQLIntegrityConstraintViolationException exception) {
		var mensagem = exception.getMessage();
//		System.out.println("tratarSqlException: "+mensagem);
		return ResponseEntity.badRequest().body(mensagem);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity tratarErro400a(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}

//	@ExceptionHandler(BadCredentialsException.class)
//	public ResponseEntity tratarErroBadCredentials() {
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais	inválidas");
//	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity tratarErroAuthentication() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity tratarErroAcessoNegado() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity tratarErro500(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + ex.getLocalizedMessage());
	}

//	@ExceptionHandler(com.auth0.jwt.exceptions.TokenExpiredException.class)
//	public ResponseEntity tratarErroJwtExpirado() {
//		System.out.println("** TRATAR ERRO JWT EXPIRADO **");
//		return ResponseEntity.badRequest().body("Token JWT inválido ou expirado");
//	}
	
	private record DadosErrosValidacao(String campo, String mensagem) {
		public DadosErrosValidacao(FieldError fieldError) {
			this(fieldError.getField(), fieldError.getDefaultMessage());
		}
	}

}