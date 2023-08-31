package dotori.guidely.domain.user.domain;

import dotori.guidely.domain.oauth.domain.OAuthProvider;
import dotori.guidely.global.BaseTime;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String email;

    @Column
    private String nickname;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;
}
