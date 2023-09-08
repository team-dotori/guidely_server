package dotori.guidely.domain.declaration.repository;

import dotori.guidely.domain.declaration.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location,Long> {
    Optional<Location> findByLatitudeAndLongitude(double latitude,double longitude);
    @Query(value = "select loc from Location loc where ST_Distance_Sphere(POINT(:longitude,:latitude), POINT(loc.longitude,loc.latitude)) <= 50")
    List<Location> findArroundByCoordinate(double latitude,double longitude);

    Location findBybuildingName(String buildingName);
}
