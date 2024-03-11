package org.lnu.smartphoneservice.service.smartphone;

import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;

import java.util.List;

public interface SmartphoneService {
    List<SmartphoneDto> findAll();
    
    SmartphoneDto findOneById(Long id);
}
