package org.lnu.smartphoneservice.controller.smartphone;

import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;
import org.lnu.smartphoneservice.service.smartphone.SmartphoneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("smartphones")
@AllArgsConstructor
public class SmartphoneController {
    private final SmartphoneService smartphoneService;
    
    @GetMapping()
    public List<SmartphoneDto> findAll() {
        return smartphoneService.findAll();
    }
    
    @GetMapping("{id}")
    public SmartphoneDto findOneById(@PathVariable Long id) {
        return smartphoneService.findOneById(id);
    }
}
