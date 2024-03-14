package org.lnu.smartphoneservice.controller.user;

import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.dto.user.UserCreateDto;
import org.lnu.smartphoneservice.dto.user.UserDto;
import org.lnu.smartphoneservice.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserCreateDto userCreateDto) {
        return userService.create(userCreateDto);
    }
}
