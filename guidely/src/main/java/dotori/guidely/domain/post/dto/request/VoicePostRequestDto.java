package dotori.guidely.domain.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "음성 게시글 요청 DTO")
public class VoicePostRequestDto {

    @Schema(description = "게시글 제목", example = "게시글 제목")
    private String title;

    @Schema(description = "음성 파일 주소", example = "url")
    private String voiceUrl;
}
