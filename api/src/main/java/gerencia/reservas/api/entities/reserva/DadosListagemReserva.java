package gerencia.reservas.api.entities.reserva;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import gerencia.reservas.api.entities.acomodacao.Acomodacao;
import gerencia.reservas.api.entities.hospede.Hospede;

public record DadosListagemReserva(
		Long id,
		 Optional<Acomodacao> acomodacao,
		 Optional<Hospede> hospede,
		 Long usuarioId,
		 String nomeUsuario,
		 
		 @DateTimeFormat(iso = ISO.DATE)
		 LocalDate dataReserva,
		 @DateTimeFormat(iso = ISO.DATE)
		 LocalDate dataEntrada,
		 @DateTimeFormat(iso = ISO.DATE)
		 LocalDate dataSaida,
		 
		 Long quantidadePessoas,
		 Timestamp dataHoraEntrada,
		 Timestamp dataHoraSaida,
		 BigDecimal valorDiaria,
		 BigDecimal valorPagoTotal,
		 String formaDePagamento,
		 String observacao) {
	
	public DadosListagemReserva(Reserva dados, Optional<Acomodacao> acomodacao, Optional<Hospede> hospede, String nomeUsuario) {
		this(dados.getId(),
				acomodacao,
				hospede,
				dados.getUsuarioId(), nomeUsuario,
				dados.getDataReserva(),dados.getDataEntrada(),dados.getDataSaída(),
				dados.getQuantidadePessoas(),dados.getDataHoraEntrada(),dados.getDataHoraSaida(),
				dados.getValorDiaria(),dados.getValorPagoTotal(),dados.getFormaPagamento(),
				dados.getObservacao());
	}
	
}
