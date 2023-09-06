package dotori.guidely.domain.post.service;

import dotori.guidely.domain.badge.scheduler.CollectBadge;
import dotori.guidely.domain.post.domain.*;
import dotori.guidely.domain.post.dto.PostDto;
import dotori.guidely.domain.post.dto.request.ModifyPostRequestDto;
import dotori.guidely.domain.post.dto.request.TextPostRequestDto;
import dotori.guidely.domain.post.dto.request.VoicePostRequestDto;
import dotori.guidely.domain.post.dto.response.PostResponseDto;
import dotori.guidely.domain.post.repository.PostContentRepository;
import dotori.guidely.domain.post.repository.PostRepository;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.repository.UserRepository;
import dotori.guidely.exception.CustomException;
import dotori.guidely.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostContentRepository contentRepository;
    private final UserRepository userRepository;

    // 단일 텍스트 게시글 생성
    @Transactional
    public PostResponseDto createTextPost(TextPostRequestDto request, Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 텍스트 게시글 내용
        TextPostContent textContent = TextPostContent.builder()
                .text(request.getContent())
                .build();

        // 텍스트 게시물 생성
        Post textPost = Post.builder()
                    .user(user)
                    .type(PostType.TEXT)
                    .content(textContent)
                    .likeCount(0)
                    .build();

        postRepository.save(textPost);
        checkCount(user);
        return toDto(textPost);
    }

    // 음성 게시글 생성
    @Transactional
    public PostResponseDto createVoicePost(VoicePostRequestDto request, Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 음성 파일의 경로
        VoicePostContent voiceContent = VoicePostContent.builder()
                .voiceUrl(request.getVoiceUrl())
                .build();

        // 음성 게시물 생성
        Post voicePost = Post.builder()
                    .user(user)
                    .type(PostType.VOICE)
                    .content(voiceContent)
                    .likeCount(0)
                    .build();

        postRepository.save(voicePost);

        return toDto(voicePost);
    }

    // 게시글 ID로 게시글 조회
    @Transactional
    public PostDto findByPostId(Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        PostDto response = PostDto.builder()
                    .postId(post.getPostId())
                    .nickname(post.getUser().getNickname())
                    .type(post.getType())
                    .content(post.getContent())
                    .likeCount(post.getLikeCount())
                    .createdDate(post.getCreatedDate())
                    .build();

        return response;
    }

    public List<PostResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();

        List<PostResponseDto> responses = new ArrayList<>();

        for (Post post : posts) {
            responses.add(toDto(post));
        }

        return responses;
    }

    // 사용자 ID로 해당 사용자의 모든 텍스트 게시글 조회
    @Transactional
    public List<PostResponseDto> findAllByUserId(Long userId) {
        List<Post> posts = userRepository.findPostsByUserId(userId);

        List<PostResponseDto> responses = new ArrayList<>();

        for (Post post : posts) {
            responses.add(toDto(post));
        }

        return responses;
    }

    @Transactional
    public Long deletePost(Long postId) {
        postRepository.deleteByPostId(postId);

        return postId;
    }

    @Transactional
    public PostResponseDto modifyPost(ModifyPostRequestDto request, Long postId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        Long contentId = post.getContent().getPostContentId();
        if (request.getType() == PostType.TEXT) {
            TextPostContent textContent = contentRepository.findTextContentByPostContentId(contentId);
            textContent.update(request.getContent());
            post.update(textContent);
        }
        else{
            VoicePostContent voiceContent = contentRepository.findVoiceContentByPostContentId(contentId);
            voiceContent.update(request.getContent());
            post.update(voiceContent);
        }

        return toDto(post);
    }

    private static PostResponseDto toDto(Post post) {
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .nickname(post.getUser().getNickname())
                .type(post.getType())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .createdDate(post.getCreatedDate())
                .build();
    }
    public void checkCount(User user){
        CollectBadge collectBadge = new CollectBadge(user,5,user.getPosts().size(),0);
        collectBadge.detectAndExecute();
    }

}
