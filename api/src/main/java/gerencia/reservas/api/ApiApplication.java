package gerencia.reservas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

//	@PostConstruct
//	public void init(){
//	// Setting Spring Boot SetTimeZone
//	//para corrigir a diferença entre a data incorporada pelo controller e a data efetivamente enviada
//	TimeZone.setDefault(TimeZone.getTimeZone("BRT"));
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
