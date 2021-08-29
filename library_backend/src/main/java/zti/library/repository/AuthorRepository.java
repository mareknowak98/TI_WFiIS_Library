package zti.library.repository;

import zti.library.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

}
