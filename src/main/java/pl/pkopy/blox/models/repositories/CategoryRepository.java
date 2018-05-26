package pl.pkopy.blox.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.pkopy.blox.models.CategoryEntity;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
    List<CategoryEntity> findAllByOrderByIdDesc();
}
