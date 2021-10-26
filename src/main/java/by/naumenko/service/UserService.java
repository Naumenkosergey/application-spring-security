package by.naumenko.service;

import by.naumenko.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;

import java.util.List;

public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    List<User> findAll();

    User getUserById(Long id);

    void removeUser(Long id);

    void updateUser(User user);

    User saveUser(User user);

    boolean saveUser(User user, Model model);
}
