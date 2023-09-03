package dotori.guidely.domain.comment.dto.response;

import dotori.guidely.domain.comment.domain.CommentContent;
import dotori.guidely.domain.comment.domain.CommentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {
    private String nickname;
    private CommentType type;
    private CommentContent content;
}
