package gerencia.reservas.api.entities.hospede;

import java.sql.Date;

public record DadosCadastroHospede(
		Long usuarioId,
		String numeroDocumento,
		String tipoDocumento,
		String nome,
		Long telefone,
		String email,
		String endereco,
		Date dataNascimento,
		Date dataCadastro) {

}
