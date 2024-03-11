package org.lnu.smartphoneservice.service.smartphone;

import org.lnu.smartphoneservice.dto.smartphone.BaseSmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;

import java.util.List;

public interface SmartphoneService {
    List<SmartphoneDto> findAll();
    
    SmartphoneDto findOneById(Long id);
    
    SmartphoneDto create(BaseSmartphoneDto baseSmartphoneDto);
}
