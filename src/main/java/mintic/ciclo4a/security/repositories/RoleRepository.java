package mintic.ciclo4a.security.repositories;

import mintic.ciclo4a.security.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository
        extends MongoRepository<Role, String> {
}
