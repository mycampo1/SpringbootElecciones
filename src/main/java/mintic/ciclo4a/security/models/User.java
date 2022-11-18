package mintic.ciclo4a.security.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    @Id
    private String id;
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;
    @DBRef
    private Role role;

    public User(
            String username,
            String email,
            String password,
            Role role
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
