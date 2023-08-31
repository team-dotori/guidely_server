package dotori.guidely.domain.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextPostResponseDto {
    private Long textPostId;
    private String nickname;
    private String title;
    private String content;
    private int likeCount;
}
