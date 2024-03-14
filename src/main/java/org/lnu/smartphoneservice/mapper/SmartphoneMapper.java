package org.lnu.smartphoneservice.mapper;

import org.lnu.smartphoneservice.dto.smartphone.BaseSmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;
import org.lnu.smartphoneservice.entity.smartphone.SmartphoneEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SmartphoneMapper {
    SmartphoneEntity toEntity(BaseSmartphoneDto baseSmartphoneDto);
    
    SmartphoneDto toDto(SmartphoneEntity smartphoneEntity);
    
    List<SmartphoneDto> toDtoList(List<SmartphoneEntity> smartphoneEntities);
}
