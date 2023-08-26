package dotori.guidely.domain.comment.domain;

import dotori.guidely.domain.board.domain.Board;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.global.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
