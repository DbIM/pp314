package webapp;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import webapp.model.User;
import webapp.service.UserService;
import webapp.service.UserServiceImpl;

@SpringBootApplication
public class CrudRestApiApplication {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        String SessionID = userService.getAllUsers()
                .getHeaders()
                .getValuesAsList("set-cookie")
                .get(0);

        User addUser = new User(3L, "James", "Brown", (byte) 25);
        userService.addUser(addUser, SessionID);

        User user = new User(3L, "Thomas", "Shelby", (byte) 29);
        userService.editUser(user, SessionID);

        userService.deleteUser(user, SessionID);
    }
}