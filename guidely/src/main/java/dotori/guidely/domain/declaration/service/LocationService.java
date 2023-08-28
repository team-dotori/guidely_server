package dotori.guidely.domain.declaration.service;

import dotori.guidely.domain.declaration.domain.Location;
import dotori.guidely.domain.declaration.dto.response.LocationResponseDto;
import dotori.guidely.domain.declaration.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
