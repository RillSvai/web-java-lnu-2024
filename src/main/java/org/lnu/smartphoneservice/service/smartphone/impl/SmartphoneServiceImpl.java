package org.lnu.smartphoneservice.service.smartphone.impl;

import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;
import org.lnu.smartphoneservice.entity.smartphone.SmartphoneEntity;
import org.lnu.smartphoneservice.mapper.SmartphoneMapper;
import org.lnu.smartphoneservice.repository.smartphone.SmartphoneRepository;
import org.lnu.smartphoneservice.service.smartphone.SmartphoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SmartphoneServiceImpl implements SmartphoneService {
    private final SmartphoneRepository smartphoneRepository;
    private final SmartphoneMapper smartphoneMapper;
    @Override
    public List<SmartphoneDto> findAll() {
        List<SmartphoneEntity> smartphoneEntities = smartphoneRepository.findAll();
        return smartphoneMapper.toDtoList(smartphoneEntities);
    }
    
    @Override
    public SmartphoneDto findOneById(Long id) {
        SmartphoneDto smartphoneDto = new SmartphoneDto();
        smartphoneDto.setId(id);
        return smartphoneDto;
    }
}
