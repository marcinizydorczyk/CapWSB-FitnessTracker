package pl.wsb.fitnesstracker.user.api;

/**
 * DTO representing a user with basic information
 * Used to listing users with only ID and full name
 * @param id
 * @param fullName
 */
public record UserListDto(Long id, String fullName) {
}
