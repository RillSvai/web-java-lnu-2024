package org.lnu.smartphoneservice.repository.smartphone;

import org.lnu.smartphoneservice.entity.smartphone.SmartphoneEntity;

import java.util.List;

public interface SmartphoneRepository {
    List<SmartphoneEntity> findAll();
    
    SmartphoneEntity findOneById(Long id);
    
    SmartphoneEntity create(SmartphoneEntity smartphoneEntity);
}
