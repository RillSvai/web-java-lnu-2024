package org.lnu.smartphoneservice.service.smartphone;

import org.lnu.smartphoneservice.dto.smartphone.BaseSmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphonePatch;

import java.util.List;

public interface SmartphoneService {
    List<SmartphoneDto> findAll();
    
    SmartphoneDto findOneById(Long id);
    
    SmartphoneDto create(BaseSmartphoneDto baseSmartphoneDto);
    
    void update(Long id, BaseSmartphoneDto baseSmartphoneDto);
    
    void patch(Long id, SmartphonePatch smartphonePatch);
    
    void delete(Long id);
}
