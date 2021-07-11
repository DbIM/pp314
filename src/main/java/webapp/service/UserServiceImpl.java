package webapp.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import webapp.model.User;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService{

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();

    public ResponseEntity<String> getAllUsers() {
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Content-Type", "application/json");
        String cookieArray = responseEntity.getHeaders().getFirst("set-cookie");
        String cookie = Arrays.stream(cookieArray.split(";"))
                .findFirst()
                .map(Object::toString)
                .orElse("");
        headers.add("Cookie", cookie);
        System.out.println();
        return responseEntity;
    }

    @Override
    public void addUser(User user, String sessionId) {
        headers.add("Cookie", sessionId);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        String result = restTemplate.postForObject(URL, requestEntity, String.class);
        System.out.println("result 1: " + result);
        System.out.println();
    }

    @Override
    public void editUser(User user, String sessionId) {
        headers.add("Cookie", sessionId);
        HttpEntity<User> requestEntity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        System.out.println("result 2: " + responseEntity.getBody());
        System.out.println();
    }

    @Override
    public void deleteUser(User user, String sessionId) {
        headers.add("Cookie", sessionId);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/" + user.getId(), HttpMethod.DELETE, requestEntity, String.class);
        System.out.println("result 3: " + responseEntity.getBody());
        System.out.println();
    }
}