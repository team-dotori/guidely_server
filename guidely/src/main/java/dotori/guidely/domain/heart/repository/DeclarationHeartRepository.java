package dotori.guidely.domain.heart.repository;

import dotori.guidely.domain.heart.domain.DeclarationHeart;
import dotori.guidely.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeclarationHeartRepository extends JpaRepository<DeclarationHeart, Long> {
    Optional<DeclarationHeart> findByUserAndDeclarationId(User user, Long declarationId);

}
