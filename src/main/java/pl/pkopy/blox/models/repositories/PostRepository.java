package pl.pkopy.blox.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pkopy.blox.models.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {

}
