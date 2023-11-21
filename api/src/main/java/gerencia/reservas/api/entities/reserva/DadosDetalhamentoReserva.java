package gerencia.reservas.api.entities.reserva;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public record DadosDetalhamentoReserva(
		 Long id,
		 Long acomodacaoId,
		 Long hospedeId,
		 Long usuarioId,
		 
		 @DateTimeFormat(iso = ISO.DATE)
		 LocalDate dataReserva,
		 @DateTimeFormat(iso = ISO.DATE)
		 LocalDate dataEntrada,
		 @DateTimeFormat(iso = ISO.DATE)
		 LocalDate dataSaida,
		 
		 Long quantidadePessoas,
		 
		 @DateTimeFormat(iso = ISO.DATE)
		 LocalDate dataHoraEntrada,
		 @DateTimeFormat(iso = ISO.DATE)
		 LocalDate dataHoraSaida,
		 BigDecimal valorDiaria,
		 BigDecimal valorPagoTotal,
		 String formaDePagamento,
		 String observacao
		) {
	
	public DadosDetalhamentoReserva(Reserva dados) {
		this(dados.getId(),dados.getAcomodacaoId(),dados.getHospedeId(),dados.getUsuarioId(),
				dados.getDataReserva(),dados.getDataEntrada(),dados.getDataSaída(),
				dados.getQuantidadePessoas(),dados.getDataHoraEntrada(),dados.getDataHoraSaida(),
				dados.getValorDiaria(),dados.getValorPagoTotal(),dados.getFormaPagamento(),
				dados.getObservacao());
	}

}
