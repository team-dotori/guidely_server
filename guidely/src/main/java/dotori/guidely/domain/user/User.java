package dotori.guidely.domain.user;

import dotori.guidely.domain.oauth.OAuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String nickname;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private OAuthProvider oAuthProvider;

    @Builder
    public User(String email, String nickname, UserType type, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.type = type;
        this.oAuthProvider = oAuthProvider;
    }
}
