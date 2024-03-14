package org.lnu.smartphoneservice.mapper;

import org.lnu.smartphoneservice.dto.user.UserCreateDto;
import org.lnu.smartphoneservice.dto.user.UserDto;
import org.lnu.smartphoneservice.entity.user.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserCreateDto userCreateDto);
    UserDto toDto(UserEntity userEntity);
    List<UserDto> toDtoList(List<UserEntity> userEntities);
}
