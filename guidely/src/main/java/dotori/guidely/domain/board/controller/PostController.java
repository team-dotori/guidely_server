package dotori.guidely.domain.board.controller;

import dotori.guidely.domain.board.domain.TextPost;
import dotori.guidely.domain.board.dto.TextPostDto;
import dotori.guidely.domain.board.dto.request.TextPostRequestDto;
import dotori.guidely.domain.board.dto.response.TextPostResponseDto;
import dotori.guidely.domain.board.repository.TextPostRepository;
import dotori.guidely.domain.board.service.PostService;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.repository.UserRepository;
import dotori.guidely.global.utils.jwt.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final ModelMapper modelMapper;

    // 게시글 생성
    @PostMapping
    public ResponseEntity<String> createTextPost(@RequestBody TextPostRequestDto request,
                                                 @RequestHeader(value = "accessToken") String accessToken){
        Long userId = authTokensGenerator.extractUserId(accessToken);

        User user = userRepository.findByUserId(userId)
                .orElseThrow(RuntimeException::new);

        TextPostDto textPostDto = modelMapper.map(request, TextPostDto.class);
        textPostDto.setUserId(userId);
        textPostDto.setLikeCount(0);

        postService.createTextPost(textPostDto, userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ok");
    }

    // 단일 게시글 조회
    @GetMapping("/{textPostId}")
    public ResponseEntity<TextPostResponseDto> getTextPost(@PathVariable Long textPostId) {
        TextPostDto textPostDto = postService.findByTextPostId(textPostId);
        System.out.println(textPostDto.getNickname());
        TextPostResponseDto response = modelMapper.map(textPostDto, TextPostResponseDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    // 사용자의 모든 게시글 조회
    @GetMapping("/user")
    public ResponseEntity<List<TextPostResponseDto>> getTextPostListByAccessToken(
                                                        @RequestHeader(value = "accessToken") String accessToken) {
        Long userId = authTokensGenerator.extractUserId(accessToken);

        List<TextPostDto> textPostDtos = postService.findAllByUserId(userId);
        List<TextPostResponseDto> responses = new ArrayList<>();
        for (TextPostDto textPostDto : textPostDtos) {
            responses.add(modelMapper.map(textPostDto, TextPostResponseDto.class));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }

    // 단일 게시글 삭제
    @DeleteMapping("/{textPostId}")
    public ResponseEntity<String> deleteTextPost(@PathVariable Long textPostId) {
        postService.deleteTextPost(textPostId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Text Post ID(%d) is deleted.", textPostId));
    }

    // 게시글 수정
    @PatchMapping("/{textPostId}")
    public ResponseEntity<TextPostResponseDto> modifyTextPost(@RequestBody TextPostRequestDto request,
                                                              @PathVariable Long textPostId) {
        TextPostDto textPostDto = modelMapper.map(request, TextPostDto.class);

        TextPostDto newDto = postService.modifyTextPost(textPostId, textPostDto);

        TextPostResponseDto response = modelMapper.map(newDto, TextPostResponseDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
