package by.naumenko.serviceimpl;

import by.naumenko.dao.RoleDao;
import by.naumenko.entity.Role;
import by.naumenko.entity.User;
import by.naumenko.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Override
    public List<Role> findAllRoles() {
        return roleDao.findAll();
    }


    public void instRole(User user) {
        for (Role role : user.getAuthorities()) {
            try {
                role.setId(roleDao.findRoleByAuthority(role.getAuthority()).getId());
            } catch (NoSuchElementException ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
