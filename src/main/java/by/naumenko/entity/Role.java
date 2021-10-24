package by.naumenko.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="role", schema = "security_storage")
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name = "role")
    private String authority;
}
