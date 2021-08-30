package zti.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zti.library.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

}
