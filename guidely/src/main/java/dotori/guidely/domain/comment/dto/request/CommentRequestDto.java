package dotori.guidely.domain.comment.dto.request;

import dotori.guidely.domain.comment.domain.CommentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    private CommentType type;
    private String content;
}
