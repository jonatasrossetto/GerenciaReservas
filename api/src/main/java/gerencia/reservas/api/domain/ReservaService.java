package gerencia.reservas.api.domain;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import gerencia.reservas.api.entities.acomodacao.AcomodacaoRepository;
import gerencia.reservas.api.entities.hospede.HospedeRepository;
import gerencia.reservas.api.entities.reserva.DadosCadastroReserva;
import gerencia.reservas.api.entities.reserva.Reserva;
import gerencia.reservas.api.entities.reserva.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository repository;

	@Autowired
	private HospedeRepository hospedeRepository;

	@Autowired
	private AcomodacaoRepository acomodacaoRepository;

	public ResponseEntity verificaConflitoDeData(DadosCadastroReserva dados) {
		System.out.println("entrada: " + dados.dataEntrada());
		System.out.println("saida: " + dados.dataSaida());
		if (dados.dataEntrada().equals(dados.dataSaida())) {
			return ResponseEntity.badRequest().body("A data de entrada e de saída são iguais");
		}
		if (dados.dataEntrada().isAfter(dados.dataSaida())) {
			return ResponseEntity.badRequest().body("A data de entrada é maior que a data de Saída");
		}
		if (!hospedeRepository.existsById(dados.hospedeId())) {
			return ResponseEntity.badRequest().body("O hóspede informado não existe");
		}
		if (!acomodacaoRepository.existsById(dados.acomodacaoId())) {
			return ResponseEntity.badRequest().body("A acomodação informada não existe");
		}
		var acomodacao = acomodacaoRepository.getReferenceById(dados.acomodacaoId());
		if (acomodacao.getCapacidadePessoas() < dados.quantidadePessoas()) {
			return ResponseEntity.badRequest().body("Quantidade de pessoas maior que a capacidade da acomodação");
		}

		var listaReservas = repository.findByAcomodacaoId(dados.acomodacaoId());
		for (Reserva elemento : listaReservas) {
			var conflitoDataEntrada = (dados.dataEntrada().isAfter(elemento.getDataEntrada())
					&& dados.dataEntrada().isBefore(elemento.getDataSaída()))
					|| dados.dataEntrada().equals(elemento.getDataEntrada());
			var conflitoDataSaida = (dados.dataSaida().isAfter(elemento.getDataEntrada())
					&& dados.dataSaida().isBefore(elemento.getDataSaída()))
					|| dados.dataSaida().isEqual(elemento.getDataSaída());
			var conflitoInterno = (dados.dataEntrada().isBefore(elemento.getDataEntrada())
					&& dados.dataSaida().isAfter(elemento.getDataSaída()));
			System.out.println("id: " + elemento.getId() + " entrada: " + elemento.getDataEntrada() + " saída: "
					+ elemento.getDataSaída() + " conflito entrada: " + conflitoDataEntrada + " conflito saída: "
					+ conflitoDataSaida + " conflito interno: " + conflitoInterno);
			if (conflitoDataEntrada || conflitoDataSaida || conflitoInterno) {
				var texto = "A acomodação já possui uma reserva dentro do período informado. Conflito entrada:"
						+ conflitoDataEntrada + " conflito saída: " + conflitoDataSaida + " conflito interno: "
						+ conflitoInterno;
				return ResponseEntity.badRequest().body("{\"error\":\"" + texto + "\"}");
			}
		}
		return null;
	}

	public ResponseEntity verificaConflitoNaReserva(LocalDate dataEntrada, LocalDate dataSaida, Long hospedId,
			Long acomodacaoId, Long quantidadePessoas, Long reservaId) {
		System.out.println("entrada: " + dataEntrada);
		System.out.println("saida: " + dataSaida);
		if (dataEntrada.equals(dataSaida)) {
			return ResponseEntity.badRequest().body("A data de entrada e de saída são iguais");
		}
		if (dataEntrada.isAfter(dataSaida)) {
			return ResponseEntity.badRequest().body("A data de entrada é maior que a data de Saída");
		}
		if (!hospedeRepository.existsById(hospedId)) {
			return ResponseEntity.badRequest().body("O hóspede informado não existe");
		}
		if (!acomodacaoRepository.existsById(acomodacaoId)) {
			return ResponseEntity.badRequest().body("A acomodação informada não existe");
		}
		var acomodacao = acomodacaoRepository.getReferenceById(acomodacaoId);
		if (acomodacao.getCapacidadePessoas() < quantidadePessoas) {
			return ResponseEntity.badRequest().body("Quantidade de pessoas maior que a capacidade da acomodação");
		}

		var listaReservas = repository.findByAcomodacaoId(acomodacaoId);
		for (Reserva elemento : listaReservas) {
			if (elemento.getId()!=reservaId) {
				var conflitoDataEntrada = (dataEntrada.isAfter(elemento.getDataEntrada())
						&& dataEntrada.isBefore(elemento.getDataSaída())) || dataEntrada.equals(elemento.getDataEntrada());
				var conflitoDataSaida = (dataSaida.isAfter(elemento.getDataEntrada())
						&& dataSaida.isBefore(elemento.getDataSaída())) || dataSaida.isEqual(elemento.getDataSaída());
				var conflitoInterno = (dataEntrada.isBefore(elemento.getDataEntrada())
						&& dataSaida.isAfter(elemento.getDataSaída()));
				System.out.println("id: " + elemento.getId() + " entrada: " + elemento.getDataEntrada() + " saída: "
						+ elemento.getDataSaída() + " conflito entrada: " + conflitoDataEntrada + " conflito saída: "
						+ conflitoDataSaida + " conflito interno: " + conflitoInterno);
				if (conflitoDataEntrada || conflitoDataSaida || conflitoInterno) {
					var texto = "A acomodação já possui uma reserva dentro do período informado. Conflito entrada:"
							+ conflitoDataEntrada + " conflito saída: " + conflitoDataSaida + " conflito interno: "
							+ conflitoInterno;
					return ResponseEntity.badRequest().body("{\"error\":\"" + texto + "\"}");
				}	
			}
			
		}
		return null;
	}

}
