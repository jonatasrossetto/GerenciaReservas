package gerencia.reservas.api.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import gerencia.reservas.api.domain.ReservaService;
import gerencia.reservas.api.entities.acomodacao.Acomodacao;
import gerencia.reservas.api.entities.acomodacao.AcomodacaoRepository;
import gerencia.reservas.api.entities.hospede.Hospede;
import gerencia.reservas.api.entities.hospede.HospedeRepository;
import gerencia.reservas.api.entities.reserva.DadosAtualizacaoReserva;
import gerencia.reservas.api.entities.reserva.DadosCadastroReserva;
import gerencia.reservas.api.entities.reserva.DadosDetalhamentoReserva;
import gerencia.reservas.api.entities.reserva.DadosListagemReserva;
import gerencia.reservas.api.entities.reserva.Reserva;
import gerencia.reservas.api.entities.reserva.ReservaRepository;
import gerencia.reservas.api.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
/**
 * Classe de controllers para atuar na tabela de Reservas
 *
 * @author Jonatas Rossetto
 * @since 1.0
 */
@RestController
@RequestMapping("/reserva")
public class ReservaController {

	@Autowired
	private ReservaRepository repository;

	@Autowired
	private HospedeRepository hospedeRepository;

	@Autowired
	private AcomodacaoRepository acomodacaoRepository;
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	TokenService tokenService;

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
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroReserva dados, UriComponentsBuilder uriBuilder, @RequestHeader HttpHeaders headers) {
		System.out.println("** CADASTRAR RESERVA ** ");
		String idUsuario = tokenService.getIdUsuarioHeader(headers);
		System.out.println("Id_usuario: "+ idUsuario);
		var conflito = reservaService.verificaConflitoDeData(dados);
		if (conflito!=null) {
			return conflito;
		}
		
		var reserva = new Reserva(dados);
		reserva.setUsuarioId(Long.parseLong(idUsuario));
		repository.save(reserva);
		System.out.println("id reserva cadastrado:" + reserva.getId());
		var uri = uriBuilder.path("/reserva/{id}").buildAndExpand(reserva.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoReserva(reserva));
	}

	@GetMapping()
	public ResponseEntity listar() {
		System.out.println("** LISTAR RESERVAS ** ");
		var page = repository.findAll();
		List<DadosListagemReserva> lista = new ArrayList<DadosListagemReserva>();
		for(Reserva reserva: page) {
			Optional<Hospede> hospede = hospedeRepository.findById(reserva.getHospedeId());
			Optional<Acomodacao> acomodacao = acomodacaoRepository.findById(reserva.getAcomodacaoId());
			lista.add(new DadosListagemReserva(reserva,acomodacao,hospede,"usuário"));
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/entradaFutura")
	public ResponseEntity listarEntradaFutura() {
		System.out.println("** LISTAR RESERVAS ** ");
		LocalDate dataHoje = LocalDate.now().minusDays(1); 
		System.out.println(dataHoje);
		var page = repository.findByDataEntradaAfter(dataHoje, Sort.by(Sort.Direction.ASC,"dataEntrada"));
		List<DadosListagemReserva> lista = new ArrayList<DadosListagemReserva>();
		for(Reserva reserva: page) {
			Optional<Hospede> hospede = hospedeRepository.findById(reserva.getHospedeId());
			Optional<Acomodacao> acomodacao = acomodacaoRepository.findById(reserva.getAcomodacaoId());
			lista.add(new DadosListagemReserva(reserva,acomodacao,hospede,"usuário"));
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/listarPorAcomodacao/{id}")
	public ResponseEntity listarPorAcomodacao(@PathVariable Long id) {
		TimeZone.setDefault(TimeZone.getTimeZone("BRT"));
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
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoReserva dados, @RequestHeader HttpHeaders headers) {
		System.out.println("** ATUALIZAR RESERVA ** ");
		String idUsuario = tokenService.getIdUsuarioHeader(headers);
		System.out.println("Id_usuario: "+ idUsuario);

		if (!repository.existsById(dados.id())) {
			return ResponseEntity.badRequest().body("Id de reserva inexistente");
		}

		var reserva = repository.getReferenceById(dados.id());
		var conflito = reservaService.verificaConflitoNaReserva(dados.dataEntrada(),
																dados.dataSaida(),
																dados.hospedeId(),
																dados.acomodacaoId(),
																dados.quantidadePessoas(),
																dados.id());
		if (conflito!=null) {
			return conflito;
		}
		
		reserva.setUsuarioId(Long.parseLong(idUsuario));
		reserva.atualizarInformacoes(dados);

		return ResponseEntity.ok(new DadosDetalhamentoReserva(reserva));
	}


	
}
