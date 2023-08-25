package dotori.guidely.domain.declaration.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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

    private RiskType risk; // LOW,MEDIUM,HIGH

    @NonNull
    private String contents;

    private int likeCount;

    private String imgUrl;

    @OneToOne
    private Location location;




}
