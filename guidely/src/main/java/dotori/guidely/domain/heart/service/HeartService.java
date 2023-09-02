package dotori.guidely.domain.heart.service;

import dotori.guidely.domain.heart.domain.Heart;
import dotori.guidely.domain.heart.dto.HeartDto;
import dotori.guidely.domain.heart.repository.HeartRepository;
import dotori.guidely.domain.post.domain.Post;
import dotori.guidely.domain.post.repository.PostRepository;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void heart(HeartDto heartDto) {
        // TODO : Add User Exception
        User user = userRepository.findByUserId(heartDto.getUserId())
                .orElseThrow(RuntimeException::new);

        // TODO: Add Heart Exception -> 이미 좋아요된 것. ALREADY_HEARTED
        if(heartRepository.findByUserAndPostId(user, heartDto.getPostId()).isPresent()){
            throw new RuntimeException();
        }

        Heart heart = Heart.builder()
                .postId(heartDto.getPostId())
                .user(user)
                .build();

        heartRepository.save(heart);

        updateHeartCount(heartDto.getPostId(), 1);
    }

    @Transactional
    public void unHeart(HeartDto heartDto) {
        // TODO : Add User Exception
        User user = userRepository.findByUserId(heartDto.getUserId())
                .orElseThrow(RuntimeException::new);

        Optional<Heart> heart = heartRepository.findByUserAndPostId(user, heartDto.getPostId());

        // TODO : "HEART_NOT_FOUND" error
        if (heart.isEmpty()) {
            throw new RuntimeException();
        }

        heartRepository.delete(heart.get());

        updateHeartCount(heartDto.getPostId(), -1);
    }

    private void updateHeartCount(Long postId, int plusOrMinus) {

        // TODO: Add post exception
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);

        post.updateHeartCount(plusOrMinus);
    }

}
