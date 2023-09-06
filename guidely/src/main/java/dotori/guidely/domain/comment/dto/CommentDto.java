package dotori.guidely.domain.comment.dto;

import dotori.guidely.domain.post.domain.Post;
import dotori.guidely.domain.comment.domain.CommentContent;
import dotori.guidely.domain.comment.domain.CommentType;
import dotori.guidely.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long commentId;
    private CommentType type;
    private Post post;
    private User user;
    private CommentContent content;
    private LocalDateTime createdDate;
}
