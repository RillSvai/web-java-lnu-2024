package org.lnu.smartphoneservice.service.smartphone;

import org.lnu.smartphoneservice.dto.common.ValueDto;
import org.lnu.smartphoneservice.dto.smartphone.BaseSmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphonePatch;
import org.lnu.smartphoneservice.dto.smartphone.query.params.SmartphoneFilterOptions;

import java.util.List;

public interface SmartphoneService {
    List<SmartphoneDto> findAll(SmartphoneFilterOptions filterOptions, Integer limit, Integer offset);
    
    ValueDto<Integer> count(SmartphoneFilterOptions filterOptions);
    
    SmartphoneDto findOneById(Long id);
    
    SmartphoneDto create(BaseSmartphoneDto baseSmartphoneDto);
    
    void update(Long id, BaseSmartphoneDto baseSmartphoneDto);
    
    void patch(Long id, SmartphonePatch smartphonePatch);
    
    void delete(Long id);
}
