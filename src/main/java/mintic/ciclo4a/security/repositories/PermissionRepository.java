package mintic.ciclo4a.security.repositories;

import mintic.ciclo4a.security.models.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository
        extends MongoRepository<Permission, String> {
}
