package dotori.guidely.domain.heart.service;

import dotori.guidely.domain.declaration.domain.Declaration;
import dotori.guidely.domain.declaration.repository.DeclarationRepository;
import dotori.guidely.domain.heart.domain.DeclarationHeart;
import dotori.guidely.domain.heart.domain.PostHeart;
import dotori.guidely.domain.heart.dto.DeclarationHeartDto;
import dotori.guidely.domain.heart.dto.PostHeartDto;
import dotori.guidely.domain.heart.repository.DeclarationHeartRepository;
import dotori.guidely.domain.heart.repository.PostHeartRepository;
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

    private final PostHeartRepository postHeartRepository;
    private final DeclarationHeartRepository declarationHeartRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final DeclarationRepository declarationRepository;

    /**
     * 게시글 좋아요 등록
     */
    @Transactional
    public void postHeart(PostHeartDto request) {
        Long userId = Long.parseLong(request.getUserId());
        Long postId = Long.parseLong(request.getPostId());

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(postHeartRepository.findByUserAndPostId(user, postId).isPresent()){
            throw new CustomException(ErrorCode.ALREADY_HEARTED);
        }

        PostHeart heart = PostHeart.builder()
                .postId(postId)
                .user(user)
                .build();

        postHeartRepository.save(heart);

        updatePostHeartCount(postId, 1);
    }

    /**
     * 게시글 좋아요 취소
     */
    @Transactional
    public void postUnHeart(PostHeartDto request) {
        Long userId = Long.parseLong(request.getUserId());
        Long postId = Long.parseLong(request.getPostId());

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Optional<PostHeart> heart = postHeartRepository.findByUserAndPostId(user, postId);

        if (heart.isEmpty()) {
            throw new CustomException(ErrorCode.HEART_NOT_FOUND);
        }

        postHeartRepository.delete(heart.get());

        updatePostHeartCount(postId, -1);
    }

    /**
     * 게시글의 하트 수를 변경
     */
    private void updatePostHeartCount(Long postId, int plusOrMinus) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        post.updateHeartCount(plusOrMinus);
    }

    /**
     * 게시글 좋아요 여부 확인
     */
    public boolean checkAlreadyPostHeart(PostHeartDto request) {
        Long userId = Long.parseLong(request.getUserId());
        Long postId = Long.parseLong(request.getPostId());
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return postHeartRepository.findByUserAndPostId(user, postId).isPresent();
    }

    /**
     * 신고 좋아요 등록
     */
    @Transactional
    public void declarationHeart(DeclarationHeartDto request) {
        Long userId = Long.parseLong(request.getUserId());
        Long declarationId = Long.parseLong(request.getDeclarationId());

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(declarationHeartRepository.findByUserAndDeclarationId(user, declarationId).isPresent()){
            throw new CustomException(ErrorCode.ALREADY_HEARTED);
        }

        DeclarationHeart heart = DeclarationHeart.builder()
                .declarationId(declarationId)
                .user(user)
                .build();

        declarationHeartRepository.save(heart);

        updateDeclarationHeartCount(declarationId, 1);
    }

    /**
     * 신고 좋아요 취소
     */
    @Transactional
    public void declarationUnHeart(DeclarationHeartDto request) {
        Long userId = Long.parseLong(request.getUserId());
        Long declarationId = Long.parseLong(request.getDeclarationId());

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Optional<DeclarationHeart> heart =
                declarationHeartRepository.findByUserAndDeclarationId(user, declarationId);

        if (heart.isEmpty()) {
            throw new CustomException(ErrorCode.HEART_NOT_FOUND);
        }

        declarationHeartRepository.delete(heart.get());

        updateDeclarationHeartCount(declarationId, -1);
    }

    /**
     * 신고의 하트 수를 변경
     */
    private void updateDeclarationHeartCount(Long declarationId, int plusOrMinus) {
        Declaration declaration = declarationRepository.findById(declarationId)
                .orElseThrow(() -> new CustomException(ErrorCode.DECLARATION_NOT_FOUND));

        declaration.updateHeartCount(plusOrMinus);
    }

    /**
     * 신고 좋아요 여부 확인
     */
    public boolean checkAlreadyDeclarationHeart(DeclarationHeartDto request) {
        Long userId = Long.parseLong(request.getUserId());
        Long declarationId = Long.parseLong(request.getDeclarationId());

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return declarationHeartRepository.findByUserAndDeclarationId(user, declarationId).isPresent();
    }

}
