package gerencia.reservas.api.entities.usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords
	UserDetails findByLogin(String login);
	
}