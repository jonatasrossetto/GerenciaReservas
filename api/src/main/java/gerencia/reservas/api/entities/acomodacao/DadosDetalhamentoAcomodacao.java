package gerencia.reservas.api.entities.acomodacao;

import java.math.BigDecimal;

public record DadosDetalhamentoAcomodacao(
		Long id,
		Long numero,
		Long capacidadePessoas,
		Long quantidadeCamas,
		BigDecimal valorDiaria) {

	public DadosDetalhamentoAcomodacao(Acomodacao dados) {
		this(dados.getId(),dados.getNumero(),dados.getCapacidadePessoas(),dados.getQuantidadeCamas(),dados.getValorDiaria());
	}
	
}
