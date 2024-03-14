package org.lnu.smartphoneservice.repository.smartphone;

import org.lnu.smartphoneservice.dto.smartphone.SmartphonePatch;
import org.lnu.smartphoneservice.dto.smartphone.query.params.SmartphoneFilterOptions;
import org.lnu.smartphoneservice.entity.smartphone.SmartphoneEntity;

import java.util.List;

public interface SmartphoneRepository {
    List<SmartphoneEntity> findAll(SmartphoneFilterOptions filterOptions, Integer limit, Integer offset);
    
    Integer count (SmartphoneFilterOptions filterOptions);
    
    SmartphoneEntity findOneById(Long id);
    
    SmartphoneEntity create(SmartphoneEntity smartphoneEntity);
    
    void update(SmartphoneEntity smartphoneEntity);
    
    void patch (Long id, SmartphonePatch smartphonePatch);
    
    void delete (Long id);
    
}
