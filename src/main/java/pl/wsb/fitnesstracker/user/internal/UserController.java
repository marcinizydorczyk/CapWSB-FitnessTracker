package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;


import java.time.LocalDate;
import java.util.List;

/**
 * REST controller providing CRUD operations on users
 */

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    /**
     * Endpoint to download list of users
     * @return list of users {@link UserDto}
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    /**
     *Endpoint to return simple information about user
     * @return simple
     */
    @GetMapping("/simple")
    public ResponseEntity<List<UserSimpleDto>> getAllSimpleUsers() {
        return ResponseEntity.ok(userService.getAllSimpleUsers());
    }

    /**
     *Endpoint to add user
     * @param userDto
     * @return
     */
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        var user = userService.createUser(userMapper.toEntity(userDto));
        return ResponseEntity.status(201).body(userMapper.toDto(user));
    }

    /**
     *Endpoint to delete user
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param email
     * @return
     */
    @GetMapping("/email")
    public ResponseEntity<List<UserEmailDto>> getUsersByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.findByEmailContainingIgnoreCase(email));
    }

    /**
     *Endpoint to update user
     * @param userId
     * @param dto
     * @return
     */
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserDto dto) {
        userService.updateUser(userId, dto);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint to find users born before specific date
     * @param date
     * @return
     */
    @GetMapping("/older/{date}")
    public ResponseEntity<List<UserDto>> getUsersOlderThan(@PathVariable LocalDate date) {
        List<UserDto> users = userService.findUsersBornBefore(date)
                .stream()
                .map(userMapper::toDto)
                .toList();
        return ResponseEntity.ok(users);
    }

    /**
     *Endpoint to find user using id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}