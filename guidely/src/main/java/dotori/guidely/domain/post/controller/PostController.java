package dotori.guidely.domain.post.controller;

import dotori.guidely.domain.post.dto.PostDto;
import dotori.guidely.domain.post.dto.request.ModifyPostRequestDto;
import dotori.guidely.domain.post.dto.request.TextPostRequestDto;
import dotori.guidely.domain.post.dto.request.VoicePostRequestDto;
import dotori.guidely.domain.post.dto.response.PostResponseDto;
import dotori.guidely.domain.post.service.PostService;
import dotori.guidely.domain.user.repository.UserRepository;
import dotori.guidely.global.utils.jwt.AuthTokensGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "게시판 API", description = "게시판 서비스의 api")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final ModelMapper modelMapper;

    @Operation(summary = "텍스트 게시글 생성", description = "텍스트 게시글을 생성하는 메서드")
    @PostMapping("/text")
    public ResponseEntity<String> createTextPost(@RequestBody TextPostRequestDto request,
                                                 @RequestHeader(value = "accessToken") String accessToken){
        Long userId = authTokensGenerator.extractUserId(accessToken);

        postService.createTextPost(request, userId);

        // TODO : 반환값 설정
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ok");
    }

    @Operation(summary = "음성 게시글 생성", description = "음성 게시글을 생성하는 메서드")
    @PostMapping("/voice")
    public ResponseEntity<String> createVoicePost(@RequestBody VoicePostRequestDto request,
                                                 @RequestHeader(value = "accessToken") String accessToken){
        Long userId = authTokensGenerator.extractUserId(accessToken);

        // TODO: Add User Exception Handler
        userRepository.findByUserId(userId).orElseThrow(RuntimeException::new);

        postService.createVoicePost(request, userId);

        // TODO : 반환값 설정
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ok");
    }

    @Operation(summary = "모든 게시글 조회", description = "생성된 모든 게시글을 조회하는 메서드")
    @GetMapping("/all")
    public ResponseEntity<List<PostResponseDto>> findAll() {
        List<PostResponseDto> responses = postService.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }

    @Operation(summary = "단일 게시글 조회", description = "게시글 ID를 통해 게시글을 조회하는 메서드")
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> findByPostId(@PathVariable Long postId) {
        PostDto postDto = postService.findByPostId(postId);

        PostResponseDto response = modelMapper.map(postDto, PostResponseDto.class);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "사용자의 모든 게시글 조회", description = "해당 사용자가 작성한 모든 게시글을 조회하는 메서드")
    @GetMapping("/user")
    public ResponseEntity<List<PostResponseDto>> findAllByAccessToken(
                                                        @RequestHeader(value = "accessToken") String accessToken) {
        Long userId = authTokensGenerator.extractUserId(accessToken);

        List<PostResponseDto> responses = postService.findAllByUserId(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responses);
    }

    @Operation(summary = "게시글 삭제", description = "ID에 해당하는 게시글을 삭제하는 메서드")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Post ID(%d) is deleted.", postId));
    }

    @Operation(summary = "게시글 수정", description = "ID에 해당하는 게시글을 수정하는 메서드")
    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponseDto> modifyPost(@RequestBody ModifyPostRequestDto request,
                                                              @PathVariable Long postId) {

        PostResponseDto response = postService.modifyPost(request, postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
