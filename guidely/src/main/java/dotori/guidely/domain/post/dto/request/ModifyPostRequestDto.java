package dotori.guidely.domain.post.dto.request;

import dotori.guidely.domain.post.domain.PostType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "게시글 수정 요청 DTO")
public class ModifyPostRequestDto {

    @Schema(description = "게시글 유형", example = "TEXT")
    private PostType type;

    @Schema(description = "수정한 게시글 제목", example = "수정된 게시글 제목")
    private String title;

    @Schema(description = "수정한 게시글 본문 내용", example = "수정된 게시글 내용")
    private String content;
}
