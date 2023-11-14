package gerencia.reservas.api.entities.usuario;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="usuarios")
@Entity(name="Usuario")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario implements UserDetails {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String login;
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", tipo=" + tipo + "]";
	}

	@NotBlank
	private String senha;
	
	@NotBlank
	private String tipo="user";
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getLogin() {
		return this.login;
	}

	public long getId() {
		return this.id;
	}
	
	public String getTipo() {
		return this.tipo;
	}

	public Usuario() {
		
	}
	
	public Usuario(DadosCadastroUsuario dados) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.login = dados.login();
		this.senha = passwordEncoder.encode(dados.senha());
		this.tipo = dados.tipo();
	}
	
	
	
	
}