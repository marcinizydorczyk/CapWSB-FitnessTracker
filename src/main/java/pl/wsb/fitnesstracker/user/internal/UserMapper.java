package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.*;

@Component
class UserMapper {

    UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    User toEntity(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }
    UserListDto toListDto(User user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        return new UserListDto(user.getId(), fullName);
    }


    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getFirstName(), user.getLastName());
    }
    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }


}
