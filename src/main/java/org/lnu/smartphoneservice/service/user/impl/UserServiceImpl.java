package org.lnu.smartphoneservice.service.user.impl;

import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.lnu.smartphoneservice.dto.user.UserCreateDto;
import org.lnu.smartphoneservice.dto.user.UserDto;
import org.lnu.smartphoneservice.dto.user.UserUpdateDto;
import org.lnu.smartphoneservice.entity.user.UserEntity;
import org.lnu.smartphoneservice.exception.DataConflictException;
import org.lnu.smartphoneservice.exception.NotFoundException;
import org.lnu.smartphoneservice.mapper.UserMapper;
import org.lnu.smartphoneservice.repository.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
                throw new DataConflictException(String.format("User with username '%s' already exist", userEntity.getUsername()));
            }
            if (cause instanceof ConstraintViolationException
                    && "users_email_key".equals(((ConstraintViolationException) cause).getConstraintName())) {
                throw new DataConflictException(String.format("User with email '%s' already exist", userEntity.getEmail()));
            }
            throw exception;
        }
        
        return userMapper.toDto(userEntity);
    }

    @Override
    public List<UserDto> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userMapper.toDtoList(userEntities);
    }

    @Override
    public UserDto findOneById(Long id) {
        UserEntity userEntity = findOneEntityById(id);
        return userMapper.toDto(userEntity);
    }

    @Override
    public void deleteOneById(Long id) {
        int affectedRows = userRepository.removeOneById(id);

        if (affectedRows == 0) {
            throw new NotFoundException(String.format("User with id '%s' not found.", id));
        }
    }

    @Override
    public void updateOneById(Long id, UserUpdateDto dto) {
        UserEntity userEntity = findOneEntityById(id);
        userMapper.update(userEntity, dto);
        userRepository.save(userEntity);
    }

    private UserEntity findOneEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("User with id '%s' not found.", id)));
    }
}
