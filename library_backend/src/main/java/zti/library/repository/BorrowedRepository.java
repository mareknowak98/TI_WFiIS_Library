package zti.library.repository;

import zti.library.model.Borrowed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zti.library.model.User;

import java.util.List;

@Repository
public interface BorrowedRepository extends CrudRepository<Borrowed, Long> {
    List<Borrowed> findByUser(User user);
}