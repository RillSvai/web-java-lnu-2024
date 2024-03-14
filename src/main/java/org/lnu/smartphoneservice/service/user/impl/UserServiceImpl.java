package org.lnu.smartphoneservice.service.user.impl;

import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.lnu.smartphoneservice.dto.user.UserCreateDto;
import org.lnu.smartphoneservice.dto.user.UserDto;
import org.lnu.smartphoneservice.entity.user.UserEntity;
import org.lnu.smartphoneservice.exception.DataConflictException;
import org.lnu.smartphoneservice.mapper.UserMapper;
import org.lnu.smartphoneservice.repository.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements org.lnu.smartphoneservice.service.user.UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        UserEntity userEntity = userMapper.toEntity(userCreateDto);
        String passwordHash = bCryptPasswordEncoder.encode(userCreateDto.getPassword());
        userEntity.setPasswordHash(passwordHash);
        try {
            userEntity = userRepository.save(userEntity);
        }
        catch (RuntimeException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof ConstraintViolationException
                    && "users_username_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                throw new DataConflictException(String.format("Smartphone with model '%s' already exist", userEntity.getUsername()));
            }
            throw exception;
        }
        
        return userMapper.toDto(userEntity);
    }
}
