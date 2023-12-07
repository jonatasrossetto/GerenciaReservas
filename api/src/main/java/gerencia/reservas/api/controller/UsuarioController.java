package gerencia.reservas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import gerencia.reservas.api.entities.usuario.DadosCadastroUsuario;
import gerencia.reservas.api.entities.usuario.Usuario;
import gerencia.reservas.api.entities.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 * Classe de controllers para atuar na tabela de usuarios
 *
 * @author Jonatas Rossetto
 * @since 1.0
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
		var usuario = new Usuario(dados);
		repository.save(usuario);
		System.out.println("id cadastrado:"+usuario.getId());
		return ResponseEntity.ok("{ \" id \" : \" " + usuario.getId() + " \" }");
	}
		
}