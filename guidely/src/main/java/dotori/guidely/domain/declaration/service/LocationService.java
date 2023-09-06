package dotori.guidely.domain.declaration.service;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.Location;
import dotori.guidely.domain.declaration.dto.LocationCoorDto;
import dotori.guidely.domain.declaration.dto.response.ListDeclarationResponseDto;
import dotori.guidely.domain.declaration.dto.response.LocationResponseDto;
import dotori.guidely.domain.declaration.repository.LocationRepository;
import dotori.guidely.exception.CustomException;
import dotori.guidely.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    public double calMean(Location location){
        double risk = 0.0;
        List<Declaration> declarationList = location.getDeclarationList();
        for(Declaration declaration : declarationList){
            switch (declaration.getRisk()){
                case LOW: risk +=0.0; break;
                case MEDIUM: risk += 1.0; break;
                case HIGH: risk += 2.0; break;
                default: risk += 0.0; break;
            }
        }
        return risk/(declarationList.size());
    }
    public List<LocationResponseDto> findAll(){
                List<Location> locations = locationRepository.findAll();
        List<LocationResponseDto> locationResponseDtos = new ArrayList<>();

        for(Location location : locations ){
            double risk = calMean(location);
            locationResponseDtos.add(LocationResponseDto.builder()
                    .location(location).riskMean(risk)
                    .build());
        }
        return locationResponseDtos;
    }

    public Optional<Location> checkLocationIsExist(double latitude, double longitude){
        return locationRepository.findByLatitudeAndLongitude(latitude, longitude);

    }

    public LocationResponseDto findByCoor(double latitude, double longitude){
        Location location = locationRepository.findByLatitudeAndLongitude(latitude, longitude)
                .orElseThrow(() -> new CustomException(ErrorCode.LOCATION_NOT_FOUND));
        double risk = calMean(location);
        return LocationResponseDto
                .builder()
                .location(location)
                .riskMean(risk)
                .build();
    }
    public List<ListDeclarationResponseDto> findById(long id){
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.LOCATION_NOT_FOUND));
        List<ListDeclarationResponseDto> declarationDtos = new ArrayList<>();
        for(Declaration declaration: location.getDeclarationList()){
            declarationDtos.add(ListDeclarationResponseDto.builder().declaration(declaration).build());
        }
        return declarationDtos;
    }

    public List<LocationResponseDto> findArroundByCoordinate(double latitude, double longitude) { //
        List<Location> arroundByCoordinate = locationRepository.findArroundByCoordinate(latitude, longitude);
        List<LocationResponseDto> locationResponseDtos = new ArrayList<>();
        for(Location location : arroundByCoordinate){
            double risk = calMean(location);
            locationResponseDtos.add(LocationResponseDto.builder().location(location).riskMean(risk).build());
        }
        return locationResponseDtos;
    }

    public List<ListDeclarationResponseDto> findByBuildingName(String buildingName){
        Location location = locationRepository.findBybuildingName(buildingName);
        List<ListDeclarationResponseDto> declarationDtos = new ArrayList<>();
        for(Declaration declaration: location.getDeclarationList()){
            declarationDtos.add(ListDeclarationResponseDto.builder().declaration(declaration).build());
        }
        return declarationDtos;
    }

    public List<List<LocationResponseDto>> findAllByCoordinate(LocationCoorDto locationCoorDto) {
        List<List<Location>> allLocation = new ArrayList<>();
        for(LocationCoorDto tmp : locationCoorDto.getLocationCoorDtos()){
            List<Location> arroundByCoordinate = new ArrayList<>();
            for(Location location : locationRepository.findArroundByCoordinate(tmp.getLatitude(), tmp.getLongitude())){
                arroundByCoordinate.add(location);
            }
            allLocation.add(arroundByCoordinate);
        }
        List<List<LocationResponseDto>> allResponseDto = new ArrayList<>();
        for(List<Location> locationList: allLocation){
            List<LocationResponseDto> locationResponseDtos = new ArrayList<>();
            for(Location location : locationList){
                double risk = calMean(location);
                locationResponseDtos.add(LocationResponseDto.builder().location(location).riskMean(risk).build());
            }
            allResponseDto.add(locationResponseDtos);
        }

        return allResponseDto;
    }
}
