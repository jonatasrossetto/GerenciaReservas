package gerencia.reservas.api.entities.acomodacao;

import java.math.BigDecimal;

public record DadosAtualizacaoAcomodacao(
		Long id,
		Long numero,
		Long capacidadePessoas,
		Long quantidadeCamas,
		BigDecimal valorDiaria) {
	
}
