package mintic.ciclo4a.security.repositories;

import mintic.ciclo4a.security.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository
        extends MongoRepository<User, String> {
    Optional<User> findUserByEmail(String email);

    @Query("{'nick': {'$regex': ?0, '$options': 'i' }}")
    List<User> findByRegexpUsername(String nick);

    User findByUsername(String username);
}
