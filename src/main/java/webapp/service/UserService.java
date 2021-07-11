package webapp.service;

import org.springframework.http.ResponseEntity;
import webapp.model.User;

public interface UserService {
    String URL = "http://91.241.64.178:7081/api/users";
    ResponseEntity<String> getAllUsers();
    void addUser(User user, String sessionId);
    void editUser(User user, String sessionId);
    void deleteUser(User user, String sessionId);
}