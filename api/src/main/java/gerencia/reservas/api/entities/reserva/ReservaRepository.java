package gerencia.reservas.api.entities.reserva;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;




public interface ReservaRepository extends JpaRepository<Reserva, Long>  {
	List<Reserva> findByAcomodacaoId(Long acomodacaoId);
}
