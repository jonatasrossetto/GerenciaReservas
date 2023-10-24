package gerencia.reservas.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "O endpoint /usuario está funcionando";
	}
}
