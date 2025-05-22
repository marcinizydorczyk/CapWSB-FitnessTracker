package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;


import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
    @GetMapping("/simple")
    public ResponseEntity<List<UserSimpleDto>> getAllSimpleUsers() {
        return ResponseEntity.ok(userService.getAllSimpleUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        var user = userService.createUser(userMapper.toEntity(userDto));
        return ResponseEntity.status(201).body(userMapper.toDto(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/email")
    public ResponseEntity<List<UserEmailDto>> getUsersByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.findByEmailContainingIgnoreCase(email));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserDto dto) {
        userService.updateUser(userId, dto);
        return ResponseEntity.noContent().build();
    }

}