package by.naumenko.dao;

import by.naumenko.entity.User;

public interface UserDao extends BaseDao<User> {
    User findByName(String name);
}
