package gerencia.reservas.api.entities.hospede;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="hospedes")
@Entity(name="Hospede")
@Getter
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
	
	public Hospede(DadosCadastroHospede dados) {
		this.usuarioId=dados.usuarioId();
		this.numeroDocumento = dados.numeroDocumento();
		this.tipoDocumento = dados.tipoDocumento();
		this.nome = dados.nome();
		this.telefone=dados.telefone();
		this.email = dados.email();
		this.endereco = dados.endereco();
		this.dataNascimento=dados.dataNascimento();
		this.dataCadastro = dados.dataCadastro();
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

	public void atualizarInformacoes(DadosAtualizacaoHospede dados) {
		if (dados.nome() != null ) {
			this.nome = dados.nome();
		}
		if (dados.email() != null ) {
			this.email = dados.email();
		}
		if (dados.numeroDocumento() != null ) {
			this.numeroDocumento = dados.numeroDocumento();
		}
		if (dados.tipoDocumento() != null ) {
			this.tipoDocumento = dados.tipoDocumento();
		}
		if (dados.endereco() != null ) {
			this.endereco= dados.endereco();
		}
		if (dados.dataNascimento() != null ) {
			this.dataNascimento = dados.dataNascimento();
		}
		if (dados.telefone() != null ) {
			this.telefone = dados.telefone();
		}
		
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Hospede(Long usuarioId, String numeroDocumento, String tipoDocumento, String nome, Long telefone,
			String email, String endereco, Date dataNascimento, Date dataCadastro) {
		
		this.usuarioId = usuarioId;
		this.numeroDocumento = numeroDocumento;
		this.tipoDocumento = tipoDocumento;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.dataNascimento = dataNascimento;
		this.dataCadastro = dataCadastro;
	}
	
	
	
	
}
