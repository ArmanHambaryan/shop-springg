package am.itspace.shopspring.service;

import am.itspace.shopspring.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void save(User user);

    void deleteById(Integer id);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    void verifyUser(String email, String token);
}
