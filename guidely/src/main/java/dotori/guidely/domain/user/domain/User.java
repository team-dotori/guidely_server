package dotori.guidely.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.heart.domain.Heart;
import dotori.guidely.domain.oauth.domain.OAuthProvider;
import dotori.guidely.domain.post.domain.Post;
import dotori.guidely.global.BaseTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Heart> hearts;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Declaration> declarationList = new ArrayList<>();

    public void addDeclaration(Declaration declaration){ //편의 메소드
        this.declarationList.add(declaration);
    }
}
