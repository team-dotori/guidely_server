package dotori.guidely.domain.declaration.controller;

import dotori.guidely.domain.declaration.dto.response.ListDclarationByLocationIdResponseDto;
import dotori.guidely.domain.declaration.dto.response.LocationResponseDto;
import dotori.guidely.domain.declaration.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/location")
@CrossOrigin(origins = "*")
public class LocationController {
    final private LocationService locationService;

    /**
     * Location 정보 모두 가져오기
     */
    @GetMapping
    public ResponseEntity<List<LocationResponseDto>> findAll(){
        return ResponseEntity.ok(locationService.findAll());
    }

    /**
     * Location Id로 신고 리스트 가져오기
     */
    @GetMapping("{id}")
    public ResponseEntity<List<ListDclarationByLocationIdResponseDto>> findById(@PathVariable long id){
        return ResponseEntity.ok(locationService.findById(id));
    }
    /**
     * 위도/경도로 특정 Location 가져오기
     */
    @GetMapping("coordinate")
    public ResponseEntity<LocationResponseDto> findByCoordinate(@RequestParam double latitude, @RequestParam double longitude){
        return ResponseEntity.ok(locationService.findByCoor(latitude,longitude));
    }
    /**
     * 위도/경도의 50m반경 위치정보(신고내역 포함) 가져오기
     */
    @GetMapping("navigation")
    public ResponseEntity<List<LocationResponseDto>> findArroundByCoordinate(@RequestParam double latitude, @RequestParam double longitude){
        return ResponseEntity.ok(locationService.findArroundByCoordinate(latitude,longitude));
    }
}
