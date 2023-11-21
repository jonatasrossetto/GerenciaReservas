package gerencia.reservas.api.entities.reserva;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public record DadosAtualizacaoReserva(
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

}
