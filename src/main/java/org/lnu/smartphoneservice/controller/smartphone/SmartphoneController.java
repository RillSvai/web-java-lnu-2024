package org.lnu.smartphoneservice.controller.smartphone;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.lnu.smartphoneservice.dto.common.ValueDto;
import org.lnu.smartphoneservice.dto.smartphone.BaseSmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphoneDto;
import org.lnu.smartphoneservice.dto.smartphone.SmartphonePatch;
import org.lnu.smartphoneservice.dto.smartphone.query.params.SmartphoneFilterOptions;
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
    @Operation(
            parameters = {
                    @Parameter(name = "model"),
                    @Parameter(name = "brand"),
                    @Parameter(name = "ram"),
                    @Parameter(name = "storage"),
                    @Parameter(name = "batteryCapacity")
            }
    )
    public List<SmartphoneDto> findAll(@Parameter(hidden = true) SmartphoneFilterOptions filterOptions,@RequestParam(required = false) Integer limit, @RequestParam(required = false) Integer offset) {
        return smartphoneService.findAll(filterOptions, limit, offset);
    }
    
    @GetMapping("count")
    @Operation(
            parameters = {
                    @Parameter(name = "model"),
                    @Parameter(name = "brand"),
                    @Parameter(name = "ram"),
                    @Parameter(name = "storage"),
                    @Parameter(name = "batteryCapacity")
            }
    )
    public ValueDto<Integer> count(@Parameter(hidden = true) SmartphoneFilterOptions filterOptions) {
        return smartphoneService.count(filterOptions);
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
    
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody BaseSmartphoneDto baseSmartphoneDto) {
        smartphoneService.update(id, baseSmartphoneDto);
    }
    
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        smartphoneService.delete(id);
    }
    
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(@PathVariable Long id, @RequestBody SmartphonePatch smartphonePatch) {
        smartphoneService.patch(id, smartphonePatch);
    }
}
