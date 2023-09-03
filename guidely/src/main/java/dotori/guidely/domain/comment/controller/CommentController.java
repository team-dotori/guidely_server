package dotori.guidely.domain.comment.controller;

import dotori.guidely.domain.comment.domain.CommentType;
import dotori.guidely.domain.comment.dto.CommentDto;
import dotori.guidely.domain.comment.dto.request.CommentRequestDto;
import dotori.guidely.domain.comment.dto.response.CommentResponseDto;
import dotori.guidely.domain.comment.service.CommentService;
import dotori.guidely.global.utils.jwt.AuthTokensGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "댓글 API", description = "댓글 서비스의 api")
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final AuthTokensGenerator authTokensGenerator;

    @Operation(summary = "댓글 생성", description = "댓글을 생성하는 메서드")
    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto request,
                                                            @PathVariable Long postId,
                                                            @RequestHeader(value = "accessToken") String accessToken) {
        Long userId = authTokensGenerator.extractUserId(accessToken);

        CommentDto commentDto;
        // 댓글 유형이 텍스트일 경우
        if (request.getType() == CommentType.TEXT) {
            commentDto = commentService.creatTextComment(request, postId, userId);
        }
        // 댓글 유형이 음성일 경우
        else {
            commentDto = commentService.creatVoiceComment(request, postId, userId);
        }

        CommentResponseDto response = CommentResponseDto.builder()
                .nickname(commentDto.getUser().getNickname())
                .type(commentDto.getType())
                .content(commentDto.getContent())
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @Operation(summary = "모든 댓글 조회", description = "해당 게시글의 모든 댓글을 조회하는 메서드")
    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAllByPostId(@PathVariable Long postId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentService.findAllByPostId(postId));
    }

    @Operation(summary = "댓글 수정", description = "댓글을 수정하는 메서드")
    @PatchMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> modifyPost(@RequestBody CommentRequestDto request,
                                                         @PathVariable Long commentId) {

        CommentResponseDto response = commentService.modifyComment(request, commentId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "댓글 삭제", description = "댓글을 삭제하는 메서드")
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(String.format("Comment ID(%d) is deleted.", commentId));
    }
}
