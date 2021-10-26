package by.naumenko.dao;

import by.naumenko.entity.Role;

import java.util.NoSuchElementException;

public interface RoleDao extends BaseDao<Role> {

    Role findRoleByAuthority(String authority) throws NoSuchElementException;
}
