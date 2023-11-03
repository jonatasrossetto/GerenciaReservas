package gerencia.reservas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gerencia.reservas.api.entities.usuario.DadosAutenticacao;
import gerencia.reservas.api.entities.usuario.Usuario;
import gerencia.reservas.api.infra.security.TokenService;
import gerencia.reservas.api.infra.security.DadosTokenJWT;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	
	@PostMapping()
	public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {
		System.out.println("** EFETUAR LOGIN ** ");
//		System.out.println("Body: "+requestBody);
//		efetuarLogin(@RequestBody DadosAutenticacao dados)
//		efetuarLogin(@RequestBody String requestBody)
		try {
			System.out.println("login: " + dados.login() + " senha: " + dados.senha());
			var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
			System.out.println("authenticationToken: " + authenticationToken);
			var authentication = manager.authenticate(authenticationToken);
			System.out.println("authentication: " + authentication);
			var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
			return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
		} catch (Exception e) {
			System.out.println("** ALGUMA COISA DEU ERRADO ** ");
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Usuário inexistente ou senha inválida");
		}

//		return ResponseEntity.ok().body("funcionou!!");
	}

}