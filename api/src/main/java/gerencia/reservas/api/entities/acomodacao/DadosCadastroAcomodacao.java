package gerencia.reservas.api.entities.acomodacao;

import java.math.BigDecimal;

public record DadosCadastroAcomodacao(
		Long numero,
		Long capacidadePessoas,
		Long quantidadeCamas,
		BigDecimal valorDiaria) {

}
