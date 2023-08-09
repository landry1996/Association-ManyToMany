package ma.enset.jpaenset.services;

import lombok.AllArgsConstructor;
import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.repositories.RoleRepository;
import ma.enset.jpaenset.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {

        User user = findUserByUserName(username);
        Role role = findRoleByRoleName(roleName);
        if (user.getRoles()!=null)
        {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

        //userRepository.save(user);
    }

    @Override
    public User authenticate(String userName, String password) {
  // c'est le travail de Spring Security
        User user = userRepository.findByUsername(userName);
        if (user==null) throw new RuntimeException("403 NOT FOUND");
        if (user.getPassword().equals(password))
        {
            return user;
        }
        throw new RuntimeException("403 NOT FOUND");
    }
}
