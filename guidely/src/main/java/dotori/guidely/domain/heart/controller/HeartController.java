package dotori.guidely.domain.heart.controller;

import dotori.guidely.domain.heart.dto.CheckDto;
import dotori.guidely.domain.heart.dto.DeclarationHeartDto;
import dotori.guidely.domain.heart.dto.PostHeartDto;
import dotori.guidely.domain.heart.service.HeartService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "사용자의 게시글 좋아요 여부 확인",
            description = "사용자가 해당 게시글에 이미 좋아요를 등록한지 확인하는 API")
    @GetMapping("/posts/check")
    public ResponseEntity<CheckDto> checkAlreadyPostHeart(@RequestBody @Valid PostHeartDto request) {
        CheckDto response = CheckDto.builder()
                .alreadyLike(heartService.checkAlreadyPostHeart(request))
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "게시글 좋아요", description = "게시글에 좋아요를 등록하는 API")
    @PostMapping("/posts")
    public ResponseEntity<PostHeartDto> postHeart(@RequestBody @Valid PostHeartDto request) {

        heartService.postHeart(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(request);
    }

    @Operation(summary = "게시글 좋아요 취소", description = "게시글에 좋아요를 취소하는 API")
    @DeleteMapping("/posts")
    public ResponseEntity<PostHeartDto> postUnHeart(@RequestBody @Valid PostHeartDto request) {
        heartService.postUnHeart(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(request);
    }

    @Operation(summary = "사용자의 신고 좋아요 여부 확인",
            description = "사용자가 해당 신고 이미 좋아요를 등록한지 확인하는 API")
    @GetMapping("/declaration/check")
    public ResponseEntity<CheckDto> checkAlreadyDeclarationHeart(@RequestBody @Valid DeclarationHeartDto request) {
        CheckDto response = CheckDto.builder()
                .alreadyLike(heartService.checkAlreadyDeclarationHeart(request))
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "신고 좋아요", description = "신고에 좋아요를 등록하는 API")
    @PostMapping("/declaration")
    public ResponseEntity<DeclarationHeartDto> declarationHeart(@RequestBody @Valid DeclarationHeartDto request) {

        heartService.declarationHeart(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(request);
    }

    @Operation(summary = "신고 좋아요 취소", description = "신고에 좋아요를 취소하는 API")
    @DeleteMapping("/declaration")
    public ResponseEntity<DeclarationHeartDto> postUnHeart(@RequestBody @Valid DeclarationHeartDto request) {
        heartService.declarationUnHeart(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(request);
    }
}
