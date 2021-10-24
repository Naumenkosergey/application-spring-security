package by.naumenko.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "security_storage")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "login")
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Transient
    private String confirmPassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", schema = "security_storage", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<>();

    @Transient
    private boolean accountNonExpired=true;
    @Transient
    private boolean accountNonLocked=true;
    @Transient
    private boolean credentialsNonExpired=true;
    @Transient
    private boolean enabled=true;
}
