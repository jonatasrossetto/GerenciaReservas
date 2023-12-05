package gerencia.reservas.api.entities.reserva;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;




public interface ReservaRepository extends JpaRepository<Reserva, Long>  {
	List<Reserva> findByAcomodacaoId(Long acomodacaoId);
	List<Reserva> findByDataEntradaAfter(LocalDate dataEntrada, Sort sort);
}
