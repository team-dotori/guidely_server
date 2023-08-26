package dotori.guidely.domain.board.domain;

import dotori.guidely.domain.comment.domain.Comment;
import dotori.guidely.global.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Board")
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private Long userId;

    private String title;

    private int likeCount;

    // TODO: fetch type 설정
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("commentId asc")
    private List<Comment> comments;
}
