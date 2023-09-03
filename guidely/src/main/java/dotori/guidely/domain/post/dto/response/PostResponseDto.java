package dotori.guidely.domain.post.dto.response;

import dotori.guidely.domain.post.domain.PostContent;
import dotori.guidely.domain.post.domain.PostType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "게시글 반환 DTO")
public class PostResponseDto {

    @Schema(description = "게시글 ID", example = "1")
    private Long postId;

    @Schema(description = "작성자의 닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "게시글 제목", example = "게시글 제목")
    private String title;

    @Schema(description = "게시글 유형", example = "TEXT")
    private PostType type;

    @Schema(description = "게시글 본문 내용")
    private PostContent content;

    @Schema(description = "게시글 좋아요 개수", example = "4")
    private int likeCount;
}
