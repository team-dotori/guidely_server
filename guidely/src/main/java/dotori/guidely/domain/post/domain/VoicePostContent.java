package dotori.guidely.domain.post.domain;

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
public class VoicePostContent extends PostContent {

    private String voiceUrl;

    public void update(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }
}
