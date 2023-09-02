package dotori.guidely.domain.heart.repository;

import dotori.guidely.domain.heart.domain.Heart;
import dotori.guidely.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByUserAndPostId(User user, Long postId);
}
