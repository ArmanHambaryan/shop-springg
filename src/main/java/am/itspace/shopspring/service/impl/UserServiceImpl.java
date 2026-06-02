package am.itspace.shopspring.service.impl;

import am.itspace.shopspring.model.User;
import am.itspace.shopspring.repository.UserRepository;
import am.itspace.shopspring.service.MailService;
import am.itspace.shopspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        user.setToken(UUID.randomUUID().toString());
        userRepository.save(user);

        Context context = new Context();
        context.setVariable("user", user);
        mailService.sendHtmlEmail(user.getEmail(), "Verify Email", "mail/verify_email", context);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void verifyUser(String email, String token) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (user.getToken().equals(token)) {
                user.setEnabled(true);
                user.setToken(null);
                userRepository.save(user);
            }
        }
    }
}
