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

import gerencia.reservas.api.entities.acomodacao.AcomodacaoRepository;
import gerencia.reservas.api.entities.hospede.HospedeRepository;
import gerencia.reservas.api.entities.reserva.DadosAtualizacaoReserva;
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

	@Autowired
	private HospedeRepository hospedeRepository;

	@Autowired
	private AcomodacaoRepository acomodacaoRepository;

	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /reserva está funcionando";
	}

	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		System.out.println("** DETALHAR RESERVA ** ");

		// verifica se existe o id informado
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
		System.out.println("entrada: "+dados.dataEntrada());
		System.out.println("saida: "+dados.dataSaida());
		if (dados.dataEntrada().equals(dados.dataSaida())) {
			return ResponseEntity.badRequest().body("A data de entrada e de saída são iguais");
		}
		if (!hospedeRepository.existsById(dados.hospedeId())) {
			return ResponseEntity.badRequest().body("O hóspede informado não existe");
		}
		if (!acomodacaoRepository.existsById(dados.acomodacaoId())) {
			return ResponseEntity.badRequest().body("A acomodação informada não existe");
		}
		var acomodacao = acomodacaoRepository.getReferenceById(dados.acomodacaoId());
		if (acomodacao.getCapacidadePessoas()<dados.quantidadePessoas()) {
			return ResponseEntity.badRequest().body("Quantidade de pessoas maior que a capacidade da acomodação");
		}
		var listaReservas = repository.findByAcomodacaoId(dados.acomodacaoId());
		for (Reserva elemento : listaReservas) {
			var conflitoDataEntrada = (dados.dataEntrada().after(elemento.getDataEntrada())&&dados.dataEntrada().before(elemento.getDataSaída()))||dados.dataEntrada().equals(elemento.getDataEntrada());
			var conflitoDataSaida = dados.dataSaida().after(elemento.getDataEntrada())&&dados.dataSaida().before(elemento.getDataSaída());
			if (conflitoDataEntrada||conflitoDataSaida) {
				return ResponseEntity.badRequest().body("A acomodação já possui uma reserva dentro do período informado.");
			}
			
		}
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

	@GetMapping("/listarPorAcomodacao/{id}")
	public ResponseEntity listarPorAcomodacao(@PathVariable Long id) {
		System.out.println("** LISTAR RESERVAS POR ID DA ACOMODAÇÃO ** ");
		var page = repository.findByAcomodacaoId(id);
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

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoReserva dados) {
		System.out.println("** ATUALIZAR RESERVA ** ");

		if (!repository.existsById(dados.id())) {
			return ResponseEntity.badRequest().body("Id de reserva inexistente");
		}

		var reserva = repository.getReferenceById(dados.id());

		reserva.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosDetalhamentoReserva(reserva));
	}

}
