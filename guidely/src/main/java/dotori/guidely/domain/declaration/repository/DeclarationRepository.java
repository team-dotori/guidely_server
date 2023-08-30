package dotori.guidely.domain.declaration.repository;

import dotori.guidely.domain.declaration.domain.Declaration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclarationRepository extends JpaRepository<Declaration,Long> {

}
