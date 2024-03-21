package org.lnu.smartphoneservice.service.user;

import org.lnu.smartphoneservice.dto.user.UserCreateDto;
import org.lnu.smartphoneservice.dto.user.UserDto;
import org.lnu.smartphoneservice.dto.user.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto create (UserCreateDto userCreateDto);

    List<UserDto> findAll();

    UserDto findOneById(Long id);

    void deleteOneById(Long id);

    void updateOneById(Long id, UserUpdateDto dto);

}
