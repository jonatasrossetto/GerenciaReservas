package gerencia.reservas.api.entities.hospede;

import java.sql.Date;

public record DadosDetalhamentoHospede(
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
	
	public DadosDetalhamentoHospede (Hospede dados) {
		this(	dados.getId(), 
				dados.getUsuarioId(), 
				dados.getNumeroDocumento(),
				dados.getTipoDocumento(),
				dados.getNome(),
				dados.getTelefone(),
				dados.getEmail(),
				dados.getEndereco(),
				dados.getDataNascimento(),
				dados.getDataCadastro());
	}
	
}
