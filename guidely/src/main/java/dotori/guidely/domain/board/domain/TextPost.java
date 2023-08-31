package dotori.guidely.domain.board.domain;

import dotori.guidely.domain.board.dto.TextPostDto;
import dotori.guidely.domain.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Text_Post")
@AllArgsConstructor
@NoArgsConstructor
public class TextPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long textPostId;

    private Long userId;

    private String title;

    private String content;

    private int likeCount;

    // TODO: fetch type 설정
    @OneToMany(mappedBy = "textPost", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("commentId asc")
    private List<Comment> comments;

    public void update(TextPostDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
