package dotori.guidely.domain.declaration.dto.response;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.Location;
import dotori.guidely.domain.declaration.domain.LocationType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class LocationResponseDto {
    private Long id;
    private double latitude;
    private double longitude;
    private String address; //도로명 주소
    private String buildingName;
    private LocationType type;

    private List<Declaration> declarationList;

    @Builder
    public LocationResponseDto(Location location){
        this.id = location.getId();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.address = location.getAddress();
        this.buildingName = location.getBuildingName();
        this.type = location.getType();
        this.declarationList = location.getDeclarationList();
    }
}
