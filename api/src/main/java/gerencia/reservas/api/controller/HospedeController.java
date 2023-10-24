package gerencia.reservas.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospede")
public class HospedeController {
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /hospede está funcionando";
	}

}
