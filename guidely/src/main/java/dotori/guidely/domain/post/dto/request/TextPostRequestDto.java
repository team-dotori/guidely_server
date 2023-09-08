package dotori.guidely.domain.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "텍스트 게시글 요청 DTO")
public class TextPostRequestDto {

    @Schema(description = "게시글 본문 내용", example = "게시글 내용")
    private String content;
}
