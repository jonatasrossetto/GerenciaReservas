package gerencia.reservas.api.entities.reserva;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public record DadosDetalhamentoReserva(
		 Long id,
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

	public DadosDetalhamentoReserva(Reserva dados) {
		this(dados.getId(),dados.getAcomodacaoId(),dados.getHospedeId(),dados.getUsuarioId(),
				dados.getDataReserva(),dados.getDataEntrada(),dados.getDataSaída(),dados.getQuantidadePessoas(),
				dados.getDataHoraEntrada(),dados.getDataHoraSaida(),dados.getValorDiaria(),dados.getValorPagoTotal(),
				dados.getFormaPagamento(),dados.getObservacao());
	}

}
