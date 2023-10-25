package gerencia.reservas.api.entities.reserva;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

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

@Table(name="reservas")
@Entity(name="Reserva")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Reserva {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="acomodacao_id")
	private Long acomodacaoId;
	
	@Column(name="hospede_id")
	private Long hospedeId;
	
	@Column(name="usuario_id")
	private Long usuarioId;
	
	private Date dataReserva;
	private Date dataEntrada;
	private Date dataSaida;
	private Long quantidadePessoas;
	private Timestamp dataHoraEntrada;
	private Timestamp dataHoraSaida;
	private BigDecimal valorDiaria;
	private BigDecimal valorPagoTotal;
	private String formaPagamento;
	private String observacao;
	
	public Reserva() {
		
	}

	public Long getId() {
		return id;
	}

	public Long getAcomodacaoId() {
		return acomodacaoId;
	}

	public Long getHospedeId() {
		return hospedeId;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public Date getDataSaída() {
		return dataSaida;
	}

	public Long getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public Timestamp getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public Timestamp getDataHoraSaida() {
		return dataHoraSaida;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}

	public BigDecimal getValorPagoTotal() {
		return valorPagoTotal;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public String getObservacao() {
		return observacao;
	}
	
	
	
	
}
