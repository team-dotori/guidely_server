package dotori.guidely.domain.post.repository;

import dotori.guidely.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long postId);

    void deleteByPostId(Long postId);

//    void deleteByTextPostId(Long textPostId);
}
