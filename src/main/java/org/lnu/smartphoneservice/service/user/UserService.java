package org.lnu.smartphoneservice.service.user;

import org.lnu.smartphoneservice.dto.user.UserCreateDto;
import org.lnu.smartphoneservice.dto.user.UserDto;

public interface UserService {
    public UserDto create (UserCreateDto userCreateDto);
}
