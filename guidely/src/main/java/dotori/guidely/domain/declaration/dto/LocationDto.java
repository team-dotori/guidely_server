package dotori.guidely.domain.declaration.dto;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.LocationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class LocationDto {
    private Long id;
    private double latitude;
    private double longitude;
    private String address; //도로명 주소
    private String buildingName;
    private LocationType type;
    private List<Declaration> declarationList = new ArrayList<>();
}
