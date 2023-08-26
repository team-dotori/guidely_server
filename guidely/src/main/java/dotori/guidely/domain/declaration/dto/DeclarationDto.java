package dotori.guidely.domain.declaration.dto;

import dotori.guidely.domain.declaration.domain.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeclarationDto {

    private DeclarationCategory category;
    private RiskType risk; // LOW,MEDIUM,HIGH
    private String contents;
    private String imgUrl;
    private double latitude;
    private double longitude;
    private String address; //도로명 주소
    private String buildingName;
    private LocationType type;

    public Declaration toEntity(){ //DTO에 필요한 부분을 빌더 패턴을 이용해 Entity를 만드는 일
        Location location = Location.builder()
                .address(address)
                .buildingName(buildingName)
                .latitude(latitude)
                .longitude(longitude)
                .type(type)
                .build();
        return Declaration.builder()
                .category(category)
                .contents(contents)
                .imgUrl(imgUrl)
                .location(location)
                .risk(risk)
                .build();
    }

    @Builder
    public DeclarationDto(DeclarationCategory category, RiskType risk, String contents, String imgUrl, double latitude, double longitude, String address, String buildingName, LocationType type) {
        this.category = category;
        this.risk = risk;
        this.contents = contents;
        this.imgUrl = imgUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.buildingName = buildingName;
        this.type = type;
    }



}
