package gerencia.reservas.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gerencia.reservas.api.entities.hospede.DadosCadastroHospede;
import gerencia.reservas.api.entities.hospede.DadosDetalhamentoHospede;
import gerencia.reservas.api.entities.hospede.Hospede;
import gerencia.reservas.api.entities.reserva.DadosCadastroReserva;
import gerencia.reservas.api.entities.reserva.DadosDetalhamentoReserva;
import gerencia.reservas.api.entities.reserva.Reserva;
import gerencia.reservas.api.entities.reserva.ReservaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/reserva")
public class ReservaController {

	@Autowired
	private ReservaRepository repository;
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /reserva está funcionando";
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		System.out.println("** DETALHAR RESERVA ** ");

		//verifica se existe o id informado
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de reserva inexistente");
		}
				
		Reserva reserva = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoReserva(reserva));
	}

	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroReserva dados, UriComponentsBuilder uriBuilder) {
		System.out.println("** CADASTRAR RESERVA ** ");
		var reserva = new Reserva(dados);
		repository.save(reserva);
		System.out.println("id reserva cadastrado:" + reserva.getId());
		var uri = uriBuilder.path("/reserva/{id}").buildAndExpand(reserva.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoReserva(reserva));
	}
	
	@GetMapping()
	public ResponseEntity listar() {
		System.out.println("** LISTAR RESERVAS ** ");
		var page = repository.findAll();
		return ResponseEntity.ok(page);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		System.out.println("** EXCLUIR RESERVA ** ");
		if (!repository.existsById(id)) {
			return ResponseEntity.badRequest().body("Id de reserva inexistente");
		}
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
