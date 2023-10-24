package gerencia.reservas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gerencia.reservas.api.entities.hospede.DadosCadastroHospede;
import gerencia.reservas.api.entities.hospede.DadosDetalhamentoHospede;
import gerencia.reservas.api.entities.hospede.Hospede;
import gerencia.reservas.api.entities.hospede.HospedeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/hospede")
public class HospedeController {
	
	@Autowired
	private HospedeRepository repository;
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /hospede está funcionando";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		System.out.println("** DETALHAR HOSPEDE ** ");

		//verifica se existe o id informado
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de hóspede inexistente");
		}
				
		Hospede hospede = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoHospede(hospede));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroHospede dados, UriComponentsBuilder uriBuilder) {
		System.out.println("** CADASTRAR HOSPEDE ** ");
		
		var hospede = new Hospede(dados);
		repository.save(hospede);
		System.out.println("id hospede cadastrado:" + hospede.getId());
		var uri = uriBuilder.path("/hospede/{id}").buildAndExpand(hospede.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoHospede(hospede));
	}

}
