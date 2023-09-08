package dotori.guidely.domain.comment.domain;

import dotori.guidely.domain.post.domain.Post;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.global.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "comment")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Enumerated(EnumType.STRING)
    private CommentType type;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contentId")
    private CommentContent content;

    public void update(CommentContent content) {
        this.content = content;
    }
}
