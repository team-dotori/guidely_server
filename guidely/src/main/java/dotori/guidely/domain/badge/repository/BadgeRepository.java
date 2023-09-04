package dotori.guidely.domain.badge.repository;

import dotori.guidely.domain.badge.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository extends JpaRepository<Badge,Long> {
}
