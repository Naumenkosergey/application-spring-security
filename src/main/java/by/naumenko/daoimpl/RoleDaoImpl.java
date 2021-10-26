package by.naumenko.daoimpl;

import by.naumenko.dao.RoleDao;
import by.naumenko.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    @Override
    public Role findRoleByAuthority(String authority) throws NoSuchElementException {
        return findAll().stream()
                .filter(role -> authority.equals(role.getAuthority()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("Role %s not found", authority)));
    }
}
