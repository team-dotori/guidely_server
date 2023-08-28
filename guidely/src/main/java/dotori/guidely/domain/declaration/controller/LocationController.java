package dotori.guidely.domain.declaration.controller;

import dotori.guidely.domain.declaration.dto.response.ListDclarationResponseDto;
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
    public ResponseEntity<List<ListDclarationResponseDto>> findById(@PathVariable long id){
        return ResponseEntity.ok(locationService.findById(id));
    }

    //TODO : 위도 경도로 위치 조회
}
