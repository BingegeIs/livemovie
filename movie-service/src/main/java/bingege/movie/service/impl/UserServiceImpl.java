package bingege.movie.service.impl;

import bingege.movie.common.api.PageQuery;
import bingege.movie.common.exception.BadRequestException;
import bingege.movie.config.property.AppProperties;
import bingege.movie.dao.UserRepository;
import bingege.movie.model.model.User;
import bingege.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppProperties appProperties;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> initAdmin() {
        if (!existByUsername()) {
            return Optional.of(add(appProperties.getNickname(), appProperties.getUsername(), appProperties.getPassword()));
        }
        return Optional.empty();
    }

    @Override
    public void fresh(String username) {
        User user = getUserByUsername(username).orElseThrow(() -> new BadRequestException("User not exist: " + username));
        user.setLatest(LocalDateTime.now());
        save(user);
    }

    @Override
    public User add(String nickname, String username, String password) {
        User user = User.builder()
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .username(username)
                .latest(LocalDateTime.now()).build();
        user.setHasDelete(false);
        return save(user);
    }

    @Override
    public Page<User> gets(PageQuery query) {
        return userRepository.findByHasDelete(false, query.page());
    }

    @Override
    public void save(Long id, String nickname) {
        User user = get(id);
        user.setNickname(nickname);
        save(user);
    }

    private User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User get(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void delete(Long id) {
        User user = get(id);
        user.setHasDelete(true);
        user.setDeleteAt(LocalDateTime.now());
        save(user);
    }

    private boolean existByUsername() {
        Optional<User> byUsername = userRepository.findByUsername(appProperties.getUsername());
        return byUsername.isPresent();
    }
}
