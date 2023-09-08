package dotori.guidely.domain.comment.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@SuperBuilder
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public abstract class CommentContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentContentId;
}
