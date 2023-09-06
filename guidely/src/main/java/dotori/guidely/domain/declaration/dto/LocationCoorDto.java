package dotori.guidely.domain.declaration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationCoorDto {
    private double latitude;
    private double longitude;

    private List<LocationCoorDto> locationCoorDtos;

    public List<LocationCoorDto> getLocationCoorDtos(){
        return locationCoorDtos;
    }
    public void setLocationCoorDtos(List<LocationCoorDto> locationCoorDtos){
        this.locationCoorDtos = locationCoorDtos;
    }
}
