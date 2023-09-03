package dotori.guidely.domain.heart.controller;

import dotori.guidely.domain.heart.dto.HeartDto;
import dotori.guidely.domain.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/heart")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    @PostMapping
    public ResponseEntity<HeartDto> heart(@RequestBody @Valid HeartDto heartDto) {

        heartService.heart(heartDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(heartDto);
    }

    @DeleteMapping
    public ResponseEntity<HeartDto> unHeart(@RequestBody @Valid HeartDto heartDto) {
        heartService.unHeart(heartDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(heartDto);
    }
}
