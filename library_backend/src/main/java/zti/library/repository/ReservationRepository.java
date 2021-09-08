package zti.library.repository;

import zti.library.model.Borrowed;
import zti.library.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zti.library.model.User;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByUser(User user);

}
