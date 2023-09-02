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
public class TextCommentContent extends CommentContent{

    private String text;

    public void update(String text) {
        this.text = text;
    }
}
