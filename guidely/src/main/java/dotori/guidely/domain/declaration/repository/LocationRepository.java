package dotori.guidely.domain.declaration.repository;

import dotori.guidely.domain.declaration.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
