package dotori.guidely.domain.declaration.service;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.domain.Location;
import dotori.guidely.domain.declaration.dto.response.ListDclarationResponseDto;
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

    public Optional<Location> findByCoor(double latitude, double longitude){
        return locationRepository.findByLatitudeAndLongitude(latitude, longitude);

    }
    public List<ListDclarationResponseDto> findById(long id){
        Location location = locationRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.LOCATION_NOT_FOUND));
        List<ListDclarationResponseDto> declarationDtos = new ArrayList<>();
        for(Declaration declaration: location.getDeclarationList()){
            declarationDtos.add(ListDclarationResponseDto.builder().declaration(declaration).build());
        }
        return declarationDtos;
    }
}
