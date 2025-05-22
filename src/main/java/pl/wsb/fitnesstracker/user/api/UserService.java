package pl.wsb.fitnesstracker.user.api;

import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {

    User createUser(User user);

    List<UserListDto> getAllUserList();
    List<UserSimpleDto> getAllSimpleUsers();
    List<UserEmailDto> findByEmailContaining(String email);
    List<UserEmailDto> findByEmailContainingIgnoreCase(String fragment);
    void deleteUser(Long userId);
    void updateUser(Long id, UserDto userDto);

}

