package dotori.guidely.domain.declaration.dto.response;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.DeclarationCategory;
import dotori.guidely.domain.declaration.domain.Location;
import dotori.guidely.domain.declaration.domain.RiskType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeclarationResponseDto {
    private DeclarationCategory category;
    private RiskType risk; // LOW,MEDIUM,HIGH
    private String contents;
    private int likeCount;
    private String imgUrl;
    private String specification;
    private Location location;
    private LocalDateTime createdTime;
    @Builder
    public DeclarationResponseDto(Declaration declaration) {
        this.category = declaration.getCategory();
        this.risk = declaration.getRisk();
        this.contents = declaration.getContents();
        this.likeCount = declaration.getLikeCount();
        this.imgUrl = declaration.getImgUrl();
        this.specification = declaration.getSpecification();
        this.location = declaration.getLocation();
        this.createdTime = declaration.getCreatedDate();
    }
}
