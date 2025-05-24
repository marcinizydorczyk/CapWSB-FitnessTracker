package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.*;

/**
 * Maps between user entity and DTOs used in the API layer
 */
@Component
class UserMapper {
    /**
     * Converts a {@link User} entity to a {@link UserDto}.
     *
     * @param user the user entity to be converted
     * @return a DTO containing user data
     */
    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Converts a {@link UserDto} to a {@link User} entity.
     *
     * @param userDto the DTO containing user data
     * @return a new {@link User} entity created from the provided DTO
     */
    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

    /**
     * Converts a {@link User} entity to a {@link UserListDto} containing only basic user data.
     *
     * @param user the {@link User} entity to convert
     * @return a {@link UserListDto} containing the user ID and full name
     */
    UserListDto toListDto(User user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        return new UserListDto(user.getId(), fullName);
    }

    /**
     * Converts a {@link User} entity to a {@link UserSimpleDto} containing only the user's first and last name.
     *
     * @param user the {@link User} entity to convert
     * @return a {@link UserSimpleDto} with the user's first and last name
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getFirstName(), user.getLastName());
    }

    /**
     * Converts a {@link User} entity to a {@link UserEmailDto} containing only the user's ID and email address.
     *
     * @param user the {@link User} entity to convert
     * @return a {@link UserEmailDto} with the user's ID and email
     */
    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }


}
