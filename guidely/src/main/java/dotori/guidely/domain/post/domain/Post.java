package dotori.guidely.domain.post.domain;

import dotori.guidely.domain.comment.domain.Comment;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.global.BaseTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Table(name = "post")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Post extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private PostType type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "postContentId")
    private PostContent content;

    private int likeCount;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("commentId asc")
    private List<Comment> comments;

    public void update(PostContent content) {
        this.content = content;
    }

    public void updateHeartCount(int plusOrMinus) {
        this.likeCount += plusOrMinus;
    }

}
