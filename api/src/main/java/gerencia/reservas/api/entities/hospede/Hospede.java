package gerencia.reservas.api.entities.hospede;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="propostas")
@Entity(name="Proposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Hospede {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="usuario_id")
	private Long usuarioId;
	
	private String numeroDocumento;
	private String tipoDocumento;
	private String nome;
	private Long telefone;
	private String email;
	private String endereco;
	private Date dataNascimento;
	private Date dataCadastro;
	
	public Hospede() {
		
	}

	public Long getId() {
		return id;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public String getNome() {
		return nome;
	}

	public Long getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	
	
	
}
