package gerencia.reservas.api.entities.reserva;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public record DadosCadastroReserva(
		 Long acomodacaoId,
		 Long hospedeId,
		 Long usuarioId,
		 Date dataReserva,
		 Date dataEntrada,
		 Date dataSaida,
		 Long quantidadePessoas,
		 Timestamp dataHoraEntrada,
		 Timestamp dataHoraSaida,
		 BigDecimal valorDiaria,
		 BigDecimal valorPagoTotal,
		 String formaDePagamento,
		 String observacao
		) {

}
