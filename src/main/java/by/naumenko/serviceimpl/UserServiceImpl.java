package by.naumenko.serviceimpl;

import by.naumenko.dao.UserDao;
import by.naumenko.entity.User;
import by.naumenko.service.RoleService;
import by.naumenko.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        return user;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public void removeUser(Long id) {
        userDao.remove(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    public boolean saveUser(User user, Model model) {
        try {
            if (user.getPassword().equals(user.getConfirmPassword())) {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                roleService.instRole(user);
                if (user.getId() == null) {
                    saveUser(user);
                } else {
                    updateUser(user);
                }
            } else {
                model.addAttribute("passwordException", true);
                return false;
            }
        } catch (PersistenceException exception) {
            model.addAttribute("persistenceException", true);
            return false;
        }
        return true;
    }
}
