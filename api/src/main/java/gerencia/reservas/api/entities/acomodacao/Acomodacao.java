package gerencia.reservas.api.entities.acomodacao;

import java.math.BigDecimal;

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

@Table(name="acomodacoes")
@Entity(name="Acomodacao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Acomodacao {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long numero;
	private Long capacidadePessoas;
	private Long quantidadeCamas;
	private BigDecimal valorDiaria;

	public Acomodacao() {
	}
	
	public Acomodacao(DadosCadastroAcomodacao dados) {
		this.numero = dados.numero();
		this.capacidadePessoas = dados.capacidadePessoas();
		this.quantidadeCamas = dados.quantidadeCamas();
		this.valorDiaria=dados.valorDiaria();
	}
	
	public void atualizarInformacoes(DadosAtualizacaoAcomodacao dados) {
		if (dados.numero() != null ) {
			this.numero = dados.numero();
		}
		if (dados.capacidadePessoas() != null ) {
			this.capacidadePessoas = dados.capacidadePessoas();
		}
		if (dados.quantidadeCamas() != null ) {
			this.quantidadeCamas = dados.quantidadeCamas();
		}
		if (dados.valorDiaria() != null ) {
			this.valorDiaria = dados.valorDiaria();
		}
		
	}

	public Long getId() {
		return id;
	}

	public Long getNumero() {
		return numero;
	}

	public Long getCapacidadePessoas() {
		return capacidadePessoas;
	}

	public Long getQuantidadeCamas() {
		return quantidadeCamas;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}
	

}