package dotori.guidely.domain.declaration.dto;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.DeclarationCategory;
import dotori.guidely.domain.declaration.domain.LocationType;
import dotori.guidely.domain.declaration.domain.RiskType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeclarationDto {
    private DeclarationCategory category;
    private RiskType risk; // LOW,MEDIUM,HIGH
    private String contents;
    private String imgUrl;
    private String specification;
    private double latitude;
    private double longitude;
    private String address; //도로명 주소
    private String buildingName;
    private LocationType type;
    public Declaration toEntity(){ //DTO에 필요한 부분을 빌더 패턴을 이용해 Entity를 만드는 일
        Declaration declaration = Declaration.builder()
                .category(category)
                .contents(contents)
                .imgUrl(imgUrl)
                .risk(risk)
                .specification(specification)
                .build();
        return declaration;
    }

}
