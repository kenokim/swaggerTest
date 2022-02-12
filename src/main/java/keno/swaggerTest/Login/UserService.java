package keno.swaggerTest.Login;

import keno.swaggerTest.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User save(String username, String password) {
        User user = findUserByUsername(username);
        if (user != null) throw new IllegalStateException("Duplicate Users");
        return userRepository.save(User.createUser(username, password));
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Page<User> findAllPaged(UserListQueryDto dto) {
        return userRepository.findAll(dto.getPageRequest());
    }

    public Page<User> findAllByQueryDto(UserListQueryDto dto) {
        return userRepository.findAllByQueryDto(dto);
    }

    @Transactional
    public void updateUserPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        user.changePassword(password);
    }

    @Transactional
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }
}
