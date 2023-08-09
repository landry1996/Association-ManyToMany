package ma.enset.jpaenset.repositories;

import ma.enset.jpaenset.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
          User findByUsername(String userName);
}
