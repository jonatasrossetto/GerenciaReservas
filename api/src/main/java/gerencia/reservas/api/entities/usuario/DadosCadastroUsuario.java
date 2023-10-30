package gerencia.reservas.api.entities.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
		
		@NotBlank(message="login é obrigatório")
		@Email(message="Formato do e-mail é inválido")
		String login,
		
		@NotBlank(message="senha é obrigatório")
		String senha, 
		
		@NotBlank(message="Tipo de usuário é obrigatório")
		String tipo
		
		) {
	
}