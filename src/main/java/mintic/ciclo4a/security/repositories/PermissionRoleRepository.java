package mintic.ciclo4a.security.repositories;

import mintic.ciclo4a.security.models.Permission;
import mintic.ciclo4a.security.models.PermissionRole;
import mintic.ciclo4a.security.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PermissionRoleRepository extends MongoRepository<PermissionRole, String> {
    List<PermissionRole> findByRole(Role role);
    List<PermissionRole> findByPermission(Permission permission);

    @Query("{'role.$id': ?0, 'permission.$id': ?1}")
    List<PermissionRole> customQuery(String role, String permission);
}
