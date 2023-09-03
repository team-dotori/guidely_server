package dotori.guidely.domain.comment.repository;

import dotori.guidely.domain.comment.domain.Comment;
import dotori.guidely.domain.comment.domain.TextCommentContent;
import dotori.guidely.domain.comment.domain.VoiceCommentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT tc FROM TextCommentContent tc WHERE tc.commentContentId = :commentContentId")
    TextCommentContent findTextContentByCommentContentId(@Param("commentContentId") Long commentContentId);

    @Query(value = "SELECT vc FROM VoiceCommentContent vc WHERE vc.commentContentId = :commentContentId")
    VoiceCommentContent findVoiceContentByCommentContentId(@Param("commentContentId") Long commentContentId);
}
