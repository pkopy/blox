package pl.pkopy.blox.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.pkopy.blox.models.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
}
