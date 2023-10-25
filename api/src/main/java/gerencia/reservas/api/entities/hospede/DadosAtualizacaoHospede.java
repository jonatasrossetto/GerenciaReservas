package gerencia.reservas.api.entities.hospede;

import java.sql.Date;

public record DadosAtualizacaoHospede(
		Long id,
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
