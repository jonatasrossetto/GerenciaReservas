package gerencia.reservas.api.entities.reserva;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataReserva;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataSaida;
	
	private Long quantidadePessoas;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataHoraEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataHoraSaida;
	
	private BigDecimal valorDiaria;
	private BigDecimal valorPagoTotal;
	private String formaPagamento;
	private String observacao;
	
	public Reserva() {
		
	}
	
	public Reserva(DadosCadastroReserva dados) {
		this.acomodacaoId = dados.acomodacaoId();
		this.hospedeId = dados.hospedeId();
		this.usuarioId = dados.usuarioId();
		this.dataReserva = dados.dataReserva();
		this.dataEntrada = dados.dataEntrada();
		this.dataSaida = dados.dataSaida();
		this.quantidadePessoas = dados.quantidadePessoas();
		this.dataHoraEntrada = dados.dataHoraEntrada();
		this.dataHoraSaida = dados.dataHoraSaida();
		this.valorDiaria = dados.valorDiaria();
		this.valorPagoTotal = dados.valorPagoTotal();
		this.formaPagamento = dados.formaDePagamento();
		this.observacao = dados.observacao();
	}
	public void atualizarInformacoes(DadosAtualizacaoReserva dados) {
		
		if (dados.acomodacaoId()!=null) {
			this.acomodacaoId = dados.acomodacaoId();	
		}
		
		if (dados.hospedeId()!=null) {
			this.hospedeId = dados.hospedeId();
		}
		
		if (dados.usuarioId()!=null) {
			this.usuarioId = dados.usuarioId();
		}
		
		if (dados.dataEntrada()!=null) {
			this.dataEntrada = dados.dataEntrada();
		}
		
		if (dados.dataSaida()!=null) {
			this.dataSaida = dados.dataSaida();
		}
		
		if (dados.quantidadePessoas()!=null) {
			this.quantidadePessoas = dados.quantidadePessoas();
		}
		
		if (dados.dataHoraEntrada()!=null) {
			this.dataHoraEntrada = dados.dataHoraEntrada();
		}
				
		if (dados.dataHoraSaida()!=null) {
			this.dataHoraSaida = dados.dataHoraSaida();
		}
		
		if (dados.valorDiaria()!=null) {
			this.valorDiaria = dados.valorDiaria();
		}
		
		if (dados.valorPagoTotal()!=null) {
			this.valorPagoTotal = dados.valorPagoTotal();
		}
		
		if (dados.formaDePagamento()!=null) {
			this.formaPagamento = dados.formaDePagamento();
		}
		
		if (dados.observacao()!=null) {
			this.observacao = dados.observacao();
		}
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

	public LocalDate getDataReserva() {
		return dataReserva;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public LocalDate getDataSaída() {
		return dataSaida;
	}

	public Long getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public LocalDate getDataHoraEntrada() {
		return dataHoraEntrada;
	}

	public LocalDate getDataHoraSaida() {
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

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	
	
	
	
	
}
