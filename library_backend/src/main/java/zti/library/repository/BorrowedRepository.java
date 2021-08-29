package zti.library.repository;

import zti.library.model.Borrowed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowedRepository extends CrudRepository<Borrowed, Long> {

}
