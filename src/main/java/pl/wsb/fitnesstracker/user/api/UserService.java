package pl.wsb.fitnesstracker.user.api;

import java.util.List;

/**
 * Interface (API) for modifying operations on {@link User} entities through the API.
 * Implementing classes are responsible for executing changes within a database transaction, whether by continuing an existing transaction or creating a new one if required.
 */
public interface UserService {
    /**
     * Create a new user
     * @param user
     * @return created user
     */
    User createUser(User user);

    /**
     *Retrun list of all users
     * @return
     */
    List<UserListDto>getAllUserList();

    /**
     * Return list of user with simple information
      * @return list of{@link UserListDto}
     */
    List<UserSimpleDto> getAllSimpleUsers();

    /** Search using email
     * Return list of user with simple information
     * @return list of{@link UserListDto}
     */
    List<UserEmailDto> findByEmailContaining(String email);

    /**
     *Search users with email igoring case
     * @param fragment
     * @return
     */
 List<UserEmailDto> findByEmailContainingIgnoreCase(String fragment);

    /**
     * Delete user
      * @param userId
     */
 void deleteUser(Long userId);

    /**
     * Update user
     * @param id
     * @param userDto
     */
    void updateUser(Long id, UserDto userDto);

}

