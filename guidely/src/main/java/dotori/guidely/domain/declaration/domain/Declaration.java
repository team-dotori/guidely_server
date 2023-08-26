package dotori.guidely.domain.declaration.domain;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long declarationId;

    @Enumerated(EnumType.STRING)
    @NonNull
    private DeclarationCategory category;

    @NonNull
    private RiskType risk; // LOW,MEDIUM,HIGH

    @NonNull
    private String contents;

    private int likeCount;

    private String imgUrl;

    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @Builder
    public Declaration(@NonNull DeclarationCategory category, RiskType risk, @NonNull String contents, String imgUrl, Location location) {
        this.category = category;
        this.risk = risk;
        this.contents = contents;
        this.imgUrl = imgUrl;
        this.location = location;
    }
}
