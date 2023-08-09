package ma.enset.jpaenset;

import ma.enset.jpaenset.entities.Role;
import ma.enset.jpaenset.entities.User;
import ma.enset.jpaenset.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class JpaEnsetApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaEnsetApplication.class, args);


    }
    @Bean
    CommandLineRunner start(UserService userService){

        return args -> {
            Stream.of("User1","User2","Admin").
                    forEach(users->{

                        var u = new User();
                        u.setUsername(users);
                        u.setPassword("12345");
                        userService.addNewUser(u);


                    });

            Stream.of("STUDENT","USER","ADMIN").
                    forEach(roles->{

                        var r = new Role();
                        r.setRoleName(roles);
                        userService.addNewRole(r);

                    });
            userService.addRoleToUser("User1","USER");
            userService.addRoleToUser("User1","STUDENT");
            userService.addRoleToUser("User2","STUDENT");
            userService.addRoleToUser("Admin","USER");
            userService.addRoleToUser("Admin","ADMIN");

         try{
             User user = userService.authenticate("User1","12345");
             System.out.println(user.getUserId());
             System.out.println(user.getUsername());
             System.out.println("Roles: ");
             user.getRoles().forEach(role -> {
                 System.out.println("Roles = "+role.toString() );
             });

         }catch (Exception e){
          e.printStackTrace();
         }

        };
    }
}
