package dotori.guidely.domain.declaration.dto.response;

import dotori.guidely.domain.declaration.domain.DeclarationCategory;
import dotori.guidely.domain.declaration.domain.Location;
import dotori.guidely.domain.declaration.domain.RiskType;
import lombok.Data;

@Data
public class DeclarationResponseDto {
    private Long declarationId;
    private DeclarationCategory category;
    private RiskType risk; // LOW,MEDIUM,HIGH
    private String contents;
    private int likeCount;
    private String imgUrl;
    private Location location;
}
