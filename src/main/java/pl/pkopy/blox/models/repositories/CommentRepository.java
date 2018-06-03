package pl.pkopy.blox.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pkopy.blox.models.CommentEntity;
import pl.pkopy.blox.models.PostEntity;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Integer> {
    List<CommentEntity> findAllByPostOrderByCreationTimeDesc(PostEntity postEntity);
}
