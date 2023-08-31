package dotori.guidely.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextPostDto {
    private Long textPostId;
    private Long userId;
    private String nickname;
    private String title;
    private String content;
    private int likeCount;
}
