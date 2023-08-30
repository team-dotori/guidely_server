package dotori.guidely.domain.declaration.service;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.Location;
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

    public List<LocationResponseDto> findAll(){
        List<Location> locations = locationRepository.findAll();
        List<LocationResponseDto> locationResponseDtos = new ArrayList<>();

        for(Location location : locations ){
            locationResponseDtos.add(LocationResponseDto.builder().location(location).build());
        }
        return locationResponseDtos;
    }

    public Optional<Location> checkLocationIsExist(double latitude, double longitude){
        return locationRepository.findByLatitudeAndLongitude(latitude, longitude);

    }

    public LocationResponseDto findByCoor(double latitude, double longitude){
        return LocationResponseDto
                .builder()
                .location(locationRepository.findByLatitudeAndLongitude(latitude, longitude)
                        .orElseThrow(() -> new CustomException(ErrorCode.LOCATION_NOT_FOUND)))
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
            locationResponseDtos.add(LocationResponseDto.builder().location(location).build());
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
}
