package org.lnu.smartphoneservice.controller.user;

import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.dto.user.UserCreateDto;
import org.lnu.smartphoneservice.dto.user.UserDto;
import org.lnu.smartphoneservice.dto.user.UserUpdateDto;
import org.lnu.smartphoneservice.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public UserDto findOneById(@PathVariable("id") Long id) {
        return userService.findOneById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOneById(@PathVariable("id") Long id) {
        userService.deleteOneById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOneById(@PathVariable("id") Long id, @RequestBody UserUpdateDto dto) {
        userService.updateOneById(id, dto);
    }

}
