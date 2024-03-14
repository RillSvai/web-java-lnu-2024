package org.lnu.smartphoneservice.service.smartphone.impl;

import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.dto.common.ValueDto;
import org.lnu.smartphoneservice.dto.smartphone.BaseSmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphonePatch;
import org.lnu.smartphoneservice.dto.smartphone.query.params.SmartphoneFilterOptions;
import org.lnu.smartphoneservice.entity.smartphone.SmartphoneEntity;
import org.lnu.smartphoneservice.exception.BadRequestException;
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
    public List<SmartphoneDto> findAll(SmartphoneFilterOptions filterOptions, Integer limit, Integer offset) {
        List<SmartphoneEntity> smartphoneEntities = smartphoneRepository.findAll(filterOptions, limit, offset);
        return smartphoneMapper.toDtoList(smartphoneEntities);
    }

    @Override
    public ValueDto<Integer> count(SmartphoneFilterOptions filterOptions) {
        Integer count =  smartphoneRepository.count(filterOptions);
        return new ValueDto<Integer>(count);
    }

    @Override
    public SmartphoneDto findOneById(Long id) {
        SmartphoneEntity smartphoneEntity = smartphoneRepository.findOneById(id);
        return smartphoneMapper.toDto(smartphoneEntity);
    }

    @Override
    public SmartphoneDto create(BaseSmartphoneDto baseSmartphoneDto) {
        SmartphoneEntity smartphoneEntity = smartphoneMapper.toEntity(baseSmartphoneDto);
        SmartphoneEntity createdSmartphoneEntity = smartphoneRepository.create(smartphoneEntity);
        return smartphoneMapper.toDto(createdSmartphoneEntity);
        
    }

    @Override
    public void update(Long id, BaseSmartphoneDto baseSmartphoneDto) {
        SmartphoneEntity smartphoneEntity = smartphoneMapper.toEntity(baseSmartphoneDto);
        smartphoneEntity.setId(id);
        smartphoneRepository.update(smartphoneEntity);
    }

    @Override
    public void patch(Long id, SmartphonePatch smartphonePatch) {
        if(smartphonePatch.isEmpty()) {
            throw new BadRequestException("Smartphone patch is empty.");
        }
        smartphoneRepository.patch(id, smartphonePatch);
    }

    @Override
    public void delete(Long id) {
        smartphoneRepository.delete(id);
    }
}
