package dotori.guidely.domain.heart.service;

import dotori.guidely.domain.heart.domain.Heart;
import dotori.guidely.domain.heart.dto.HeartDto;
import dotori.guidely.domain.heart.repository.HeartRepository;
import dotori.guidely.domain.post.domain.Post;
import dotori.guidely.domain.post.repository.PostRepository;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.repository.UserRepository;
import dotori.guidely.exception.CustomException;
import dotori.guidely.exception.ErrorCode;
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
        User user = userRepository.findByUserId(heartDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(heartRepository.findByUserAndPostId(user, heartDto.getPostId()).isPresent()){
            throw new CustomException(ErrorCode.ALREADY_HEARTED);
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
        User user = userRepository.findByUserId(heartDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Optional<Heart> heart = heartRepository.findByUserAndPostId(user, heartDto.getPostId());

        if (heart.isEmpty()) {
            throw new CustomException(ErrorCode.HEART_NOT_FOUND);
        }

        heartRepository.delete(heart.get());

        updateHeartCount(heartDto.getPostId(), -1);
    }

    private void updateHeartCount(Long postId, int plusOrMinus) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        post.updateHeartCount(plusOrMinus);
    }

}
