package by.naumenko.service;

import by.naumenko.entity.Role;
import by.naumenko.entity.User;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();

    void instRole(User user);
}
