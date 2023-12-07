package gerencia.reservas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gerencia.reservas.api.entities.hospede.DadosAtualizacaoHospede;
import gerencia.reservas.api.entities.hospede.DadosCadastroHospede;
import gerencia.reservas.api.entities.hospede.DadosDetalhamentoHospede;
import gerencia.reservas.api.entities.hospede.Hospede;
import gerencia.reservas.api.entities.hospede.HospedeRepository;
import gerencia.reservas.api.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/hospede")
public class HospedeController {
	
	@Autowired
	private HospedeRepository repository;
	
	@Autowired
	TokenService tokenService;
	
	@GetMapping("/hello")
	public String HelloWorld() {
		System.out.println("/hospede/hello controller");
		return "O endpoint /hospede está funcionando";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		System.out.println("** DETALHAR HOSPEDE ** ");
		System.out.println("id: "+id);

		//verifica se existe o id informado
		if (!repository.existsById(id)) {
			System.out.println("Não existe id informado");
			return ResponseEntity.badRequest().body("Id de hóspede inexistente");
		}
		if (repository.existsById(id)) {
			System.out.println("Existe id informado");
		}
		
		Hospede hospede = repository.getReferenceById(id);
		System.out.println("hospede.getNome(): "+hospede.getNome());
		
		return ResponseEntity.ok(new DadosDetalhamentoHospede(hospede));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroHospede dados, UriComponentsBuilder uriBuilder, @RequestHeader HttpHeaders headers) {
		System.out.println("** CADASTRAR HOSPEDE ** ");
		String idUsuario = tokenService.getIdUsuarioHeader(headers);
		System.out.println("Id_usuario: "+ idUsuario);
		var hospede = new Hospede(dados);
		hospede.setUsuarioId(Long.parseLong(idUsuario));
		repository.save(hospede);
		System.out.println("id hospede cadastrado:" + hospede.getId());
		var uri = uriBuilder.path("/hospede/{id}").buildAndExpand(hospede.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoHospede(hospede));
	}

	@GetMapping()
	public ResponseEntity listar() {
		System.out.println("** LISTAR HÓSPEDES ** ");
						
		var page = repository.findAll();
		return ResponseEntity.ok(page);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		System.out.println("** EXCLUIR HOSPEDE ** ");
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de hospede inexistente");
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoHospede dados, @RequestHeader HttpHeaders headers) {
		System.out.println("** ATUALIZAR HOSPEDE ** ");
		String idUsuario = tokenService.getIdUsuarioHeader(headers);
		System.out.println("Id_usuario: "+ idUsuario);
		if (!repository.existsById(dados.id())) {
			return ResponseEntity.badRequest().body("Id de hospede inexistente");
		}
		
		var hospede = repository.getReferenceById(dados.id());
		hospede.setUsuarioId(Long.parseLong(idUsuario));
		hospede.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoHospede(hospede));
	}

	
}
