package dotori.guidely.domain.post.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@SuperBuilder
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public abstract class PostContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postContentId;
}
