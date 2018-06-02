package pl.pkopy.blox.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.pkopy.blox.models.LoginEntity;

import java.util.Optional;


@Repository
public interface LoginRepository extends CrudRepository<LoginEntity, Integer> {
    LoginEntity findByUser(String text);
    String findByPassword(String text);
}
