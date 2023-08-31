package dotori.guidely.domain.board.repository;

import dotori.guidely.domain.board.domain.TextPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TextPostRepository extends JpaRepository<TextPost, Long> {
    Optional<TextPost> findByTextPostId(Long textPostId);

    List<TextPost> findAllByUserId(Long userId);

    void deleteByTextPostId(Long textPostId);
}
