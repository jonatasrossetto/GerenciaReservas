package gerencia.reservas.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import gerencia.reservas.api.entities.hospede.Hospede;
import gerencia.reservas.api.entities.hospede.HospedeRepository;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

@ActiveProfiles("test")
@SpringBootTest
class HospedeControllerTest {

	@Autowired
	private HospedeController hospedeController;

	@Autowired
	private HospedeRepository repository;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.hospedeController);
	}

	private Long cadastrarHospede(Long usuarioId, String numeroDocumento, String tipoDocumento, String nome,
			Long telefone, String email, String endereco, Date dataNascimento, Date dataCadastro) {
		var hospede = new Hospede(usuarioId, numeroDocumento, tipoDocumento, nome, telefone, email, endereco,
				dataNascimento, dataCadastro);
		repository.save(hospede);
		return hospede.getId();
	}

	@Test
	void testHelloWorld() {
		System.out.println("testHelloWorld");
		var resposta = get("/hospede/hello").asString();
		System.out.println(resposta);
		assertEquals("O endpoint /hospede está funcionando", resposta);
	}

	@Test  @Transactional
	void testDetalharHospede() {
		var idHospede1 = cadastrarHospede(1L, "123", "PASSAPORTE", "Hóspede 1", 5511000000000L, "hospede1@email.com",
				"rua, bairro, cidade, pais", Date.valueOf("2000-01-15"), Date.valueOf("2023-12-01"));
		System.out.println("idHospede1: " + idHospede1);
		var resposta = get("/hospede/"+idHospede1).body().asString();
		System.out.println("resposta: "+resposta);
		repository.deleteById(idHospede1);
	}

}
