package dotori.guidely.domain.declaration.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dotori.guidely.domain.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "declaration_id")
    private Long declarationId;

    @Enumerated(EnumType.STRING)
    @NonNull
    private DeclarationCategory category;

    @NonNull
    private RiskType risk; // LOW,MEDIUM,HIGH

    @NonNull
    private String contents;

    @NonNull
    private String specification;

    private int likeCount;

    private String imgUrl;
// 상세 분류, 이미지url
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name="location_id")
    private Location location;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;
    //TODO user 1 declaration 다 구현, 클라이언트에서 accessToken 받으면 userId로 설정하기 (JwtTokenProvider.class)

    @Builder
    public Declaration(@NonNull DeclarationCategory category, RiskType risk, @NonNull String contents, String imgUrl, @NonNull String specification) {
        this.category = category;
        this.risk = risk;
        this.contents = contents;
        this.imgUrl = imgUrl;
        this.specification = specification;

    }
    public void setLocation(Location location){ //편의 메소드
        if(this.location !=null){
            this.location.getDeclarationList().remove(this);
        }
        this.location = location;
    }
    public void setUser(User user){
        if(this.user !=null){
            this.user.getDeclarationList().remove(this);
        }
        this.user = user;
        if(!user.getDeclarationList().contains(this)){
            user.getDeclarationList().add(this);
        }
    }

    public void update(RiskType risk, String contents,String imgUrl){
        this.risk = risk;
        this.contents = contents;
        this.imgUrl = imgUrl;
    }
}
