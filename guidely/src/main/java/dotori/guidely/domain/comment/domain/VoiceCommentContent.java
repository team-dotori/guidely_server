package dotori.guidely.domain.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoiceCommentContent extends CommentContent {

    private String voiceUrl;

    public void update(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }
}
