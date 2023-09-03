package dotori.guidely.domain.declaration.dto.response;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.DeclarationCategory;
import dotori.guidely.domain.declaration.domain.RiskType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListDeclarationResponseDto { //신고 엔티티만
    private Long id;
    private DeclarationCategory category;
    private RiskType risk; // LOW,MEDIUM,HIGH
    private String contents;
    private int likeCount;
    private String imgUrl;
    private String specification;

    private LocalDateTime createdDate;

    @Builder
    public ListDeclarationResponseDto(Declaration declaration) {
        this.id = declaration.getDeclarationId();
        this.category = declaration.getCategory();
        this.risk = declaration.getRisk();
        this.contents = declaration.getContents();
        this.imgUrl = declaration.getImgUrl();
        this.specification = declaration.getSpecification();
        this.createdDate = declaration.getCreatedDate();
    }
}
