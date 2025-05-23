package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.*;
import pl.wsb.fitnesstracker.user.api.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(final User user) {
        log.info("Creating User {}", user);
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserListDto> getAllUserList() {
        return userRepository.findAll().stream()
                .map(userMapper::toListDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }
    @Override
    public List<UserEmailDto> findByEmailContaining(String email) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(email.toLowerCase()))
                .map(userMapper::toEmailDto)
                .toList();
    }
    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


    @Override
    public List<UserEmailDto> findByEmailContainingIgnoreCase(String fragment) {
        return userRepository.findAll().stream()
                .filter(user -> user.getEmail().toLowerCase().contains(fragment.toLowerCase()))
                .map(userMapper::toEmailDto)
                .toList();
    }
    @Override
    public void updateUser(Long id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.updateFrom(dto);
        userRepository.save(user);
    }
    public List<User> findUsersBornBefore(LocalDate date) {
        return userRepository.findAllByBirthdateBefore(date);
    }




}