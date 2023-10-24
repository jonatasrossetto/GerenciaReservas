package gerencia.reservas.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acomodacao")
public class AcomodacaoController {
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /acomodacao está funcionando";
	}

}
