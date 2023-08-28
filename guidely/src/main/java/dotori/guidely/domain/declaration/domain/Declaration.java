package dotori.guidely.domain.declaration.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    //TODO user 1 declaration 다 구현

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
        if(!location.getDeclarationList().contains(this)){
            location.getDeclarationList().add(this);
        }

    }
    public void update(RiskType risk, String contents,String imgUrl){
        this.risk = risk;
        this.contents = contents;
        this.imgUrl = imgUrl;
    }
}
