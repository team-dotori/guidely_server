package dotori.guidely.domain.declaration.controller;

import dotori.guidely.domain.declaration.dto.response.ListDeclarationResponseDto;
import dotori.guidely.domain.declaration.dto.response.LocationResponseDto;
import dotori.guidely.domain.declaration.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "위치 정보 API", description = "위치 정보 서비스의 api")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/location")
@CrossOrigin(origins = "*")
public class LocationController {
    final private LocationService locationService;

    /**
     * Location 정보 모두 가져오기
     */
    @Operation(summary = "모든 위치 정보 조회", description = "모든 위치 정보를 조회하는 API")
    @GetMapping
    public ResponseEntity<List<LocationResponseDto>> findAll(){
        return ResponseEntity.ok(locationService.findAll());
    }

    /**
     * Location Id로 신고 리스트 가져오기
     */
    @Operation(summary = "특정 위치의 모든 신고 조회", description = "특정 위치에 등록된 모든 신고 정보를 조회하는 API")
    @GetMapping("{id}")
    public ResponseEntity<List<ListDeclarationResponseDto>> findById(@PathVariable long id){
        return ResponseEntity.ok(locationService.findById(id));
    }

    /**
     * 위도/경도로 특정 Location 가져오기
     */
    @Operation(summary = "위도/경도를 통해 위치 조회", description = "위도와 경도를 통해 위치를 반환하는 API")
    @GetMapping("coordinate")
    public ResponseEntity<LocationResponseDto> findByCoordinate(@RequestParam double latitude, @RequestParam double longitude){
        return ResponseEntity.ok(locationService.findByCoor(latitude,longitude));
    }

    /**
     * 위도/경도의 50m반경 위치정보(신고내역 포함) 가져오기
     */
    @Operation(summary = "위도/경도의 50m 반경 위치 정보, 신고 정보 조회",
            description = "주어진 위도/경도의 50m 반경의 위치 정보와 신고 정보를 조회하는 API")
    @GetMapping("navigation")
    public ResponseEntity<List<LocationResponseDto>> findArroundByCoordinate(@RequestParam double latitude, @RequestParam double longitude){
        return ResponseEntity.ok(locationService.findArroundByCoordinate(latitude,longitude));
    }

    @Operation(summary = "건물명으로 신고 정보 조회", description = "해당 건물에 등록된 모든 신고 정보를 조회하는 API")
    @GetMapping("find")
    public ResponseEntity<List<ListDeclarationResponseDto>> findByBuildingName(@RequestParam String buildingName){
        return ResponseEntity.ok(locationService.findByBuildingName(buildingName));
    }
}
