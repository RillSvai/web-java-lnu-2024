package org.lnu.smartphoneservice.controller.smartphone;

import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.dto.smartphone.BaseSmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;
import org.lnu.smartphoneservice.service.smartphone.SmartphoneService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public SmartphoneDto create(@RequestBody BaseSmartphoneDto baseSmartphoneDto) {
        return smartphoneService.create(baseSmartphoneDto);
    }
}
