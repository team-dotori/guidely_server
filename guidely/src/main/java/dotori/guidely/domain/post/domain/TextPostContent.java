package dotori.guidely.domain.post.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TextPostContent extends PostContent {

    private String text;

    public void update(String text) {
        this.text = text;
    }
}
