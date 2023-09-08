package dotori.guidely.domain.heart.repository;

import dotori.guidely.domain.heart.domain.PostHeart;
import dotori.guidely.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostHeartRepository extends JpaRepository<PostHeart, Long> {
    Optional<PostHeart> findByUserAndPostId(User user, Long postId);
}
