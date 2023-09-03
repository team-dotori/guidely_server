package dotori.guidely.domain.post.service;

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
    public Long createTextPost(TextPostRequestDto request, Long userId) {
        // TODO: userException 추가
        User user = userRepository.findByUserId(userId)
                .orElseThrow(RuntimeException::new);

        // 텍스트 게시글 내용
        TextPostContent textContent = TextPostContent.builder()
                .text(request.getContent())
                .build();

        // 텍스트 게시물 생성
        Post textPost = Post.builder()
                    .user(user)
                    .title(request.getTitle())
                    .type(PostType.TEXT)
                    .content(textContent)
                    .likeCount(0)
                    .build();

        postRepository.save(textPost);

        return textPost.getPostId();
    }

    // 음성 게시글 생성
    @Transactional
    public Long createVoicePost(VoicePostRequestDto request, Long userId) {
        // TODO: userException 추가
        User user = userRepository.findByUserId(userId)
                .orElseThrow(RuntimeException::new);

        // 음성 파일의 경로
        VoicePostContent voiceContent = VoicePostContent.builder()
                .voiceUrl(request.getVoiceUrl())
                .build();

        // 음성 게시물 생성
        Post voicePost = Post.builder()
                    .user(user)
                    .title(request.getTitle())
                    .type(PostType.VOICE)
                    .content(voiceContent)
                    .likeCount(0)
                    .build();

        postRepository.save(voicePost);

        return voicePost.getPostId();
    }

    // 게시글 ID로 게시글 조회
    @Transactional
    public PostDto findByPostId(Long postId) {

        // TODO: Add Post Exception Handler
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(RuntimeException::new);

        PostDto response = PostDto.builder()
                    .postId(post.getPostId())
                    .nickname(post.getUser().getNickname())
                    .title(post.getTitle())
                    .type(post.getType())
                    .content(post.getContent())
                    .likeCount(post.getLikeCount())
                    .build();

        return response;
    }

    public List<PostResponseDto> findAll() {
        List<Post> posts = postRepository.findAll();

        List<PostResponseDto> responses = new ArrayList<>();

        for (Post post : posts) {
            responses.add(PostResponseDto.builder()
                    .postId(post.getPostId())
                    .nickname(post.getUser().getNickname())
                    .title(post.getTitle())
                    .type(post.getType())
                    .content(post.getContent())
                    .likeCount(post.getLikeCount())
                    .build()
            );
        }

        return responses;
    }

    // 사용자 ID로 해당 사용자의 모든 텍스트 게시글 조회
    @Transactional
    public List<PostResponseDto> findAllByUserId(Long userId) {
        List<Post> posts = userRepository.findPostsByUserId(userId);

        List<PostResponseDto> responses = new ArrayList<>();

        for (Post post : posts) {
            responses.add(PostResponseDto.builder()
                    .postId(post.getPostId())
                    .nickname(post.getUser().getNickname())
                    .title(post.getTitle())
                    .type(post.getType())
                    .content(post.getContent())
                    .likeCount(post.getLikeCount())
                    .build()
            );
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
        // TODO: Add Post Exception Handler
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(RuntimeException::new);

        Long contentId = post.getContent().getPostContentId();
        if (request.getType() == PostType.TEXT) {
            TextPostContent textContent = contentRepository.findTextContentByPostContentId(contentId);
            textContent.update(request.getContent());
            post.update(request.getTitle(), textContent);
        }
        else{
            VoicePostContent voiceContent = contentRepository.findVoiceContentByPostContentId(contentId);
            voiceContent.update(request.getContent());
            post.update(request.getTitle(), voiceContent);
        }

        PostResponseDto response = PostResponseDto.builder()
                .postId(post.getPostId())
                .nickname(post.getUser().getNickname())
                .title(post.getTitle())
                .type(post.getType())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .build();

        return response;
    }

}
