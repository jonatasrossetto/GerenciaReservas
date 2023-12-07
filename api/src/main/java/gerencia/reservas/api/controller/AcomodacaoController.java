package gerencia.reservas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gerencia.reservas.api.entities.acomodacao.Acomodacao;
import gerencia.reservas.api.entities.acomodacao.AcomodacaoRepository;
import gerencia.reservas.api.entities.acomodacao.DadosAtualizacaoAcomodacao;
import gerencia.reservas.api.entities.acomodacao.DadosCadastroAcomodacao;
import gerencia.reservas.api.entities.acomodacao.DadosDetalhamentoAcomodacao;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 * Classe de controllers para atuar na tabela de Acomodacoes
 *
 * @author Jonatas Rossetto
 * @since 1.0
 */
@RestController
@RequestMapping("/acomodacao")
public class AcomodacaoController {
	
	@Autowired
	private AcomodacaoRepository repository;
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /acomodacao está funcionando";
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		System.out.println("** DETALHAR ACOMODAÇÃO ** ");

		//verifica se existe o id informado
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de acomodação inexistente");
		}
				
		Acomodacao acomodacao = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoAcomodacao(acomodacao));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAcomodacao dados, UriComponentsBuilder uriBuilder) {
		System.out.println("** CADASTRAR ACOMODAÇÃO ** ");
		//implementar: validar se numero do quarto já existe antes de tentar salvar na tabela
		var acomodacao = new Acomodacao(dados);
		repository.save(acomodacao);
		
		System.out.println("id acomodação cadastrado:" + acomodacao.getId());
		var uri = uriBuilder.path("/acomodacao/{id}").buildAndExpand(acomodacao.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoAcomodacao(acomodacao));
	}

	@GetMapping()
	public ResponseEntity listar() {
		System.out.println("** LISTAR ACOMODAÇÕES ** ");
		var page = repository.findAll();
		return ResponseEntity.ok(page);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		System.out.println("** EXCLUIR ACOMODAÇÃO ** ");
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de acomodação inexistente");
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAcomodacao dados) {
		System.out.println("** ATUALIZAR ACOMODAÇÃO ** ");
		
		if (!repository.existsById(dados.id())) {
			return ResponseEntity.badRequest().body("Id de acomodação inexistente");
		}
		
		var acomodacao = repository.getReferenceById(dados.id());
		
		acomodacao.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoAcomodacao(acomodacao));
	}
	
}
