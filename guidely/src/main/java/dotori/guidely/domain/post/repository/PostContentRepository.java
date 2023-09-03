package dotori.guidely.domain.post.repository;

import dotori.guidely.domain.post.domain.PostContent;
import dotori.guidely.domain.post.domain.TextPostContent;
import dotori.guidely.domain.post.domain.VoicePostContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostContentRepository extends JpaRepository<PostContent, Long> {

    @Query(value = "SELECT tc FROM TextPostContent tc WHERE tc.postContentId = :postContentId")
    TextPostContent findTextContentByPostContentId(@Param("postContentId") Long postContentId);

    @Query(value = "SELECT vc FROM VoicePostContent vc WHERE vc.postContentId = :postContentId")
    VoicePostContent findVoiceContentByPostContentId(@Param("postContentId") Long postContentId);
}
