package dotori.guidely.domain.comment.service;

import dotori.guidely.domain.post.domain.Post;
import dotori.guidely.domain.post.repository.PostRepository;
import dotori.guidely.domain.comment.domain.Comment;
import dotori.guidely.domain.comment.domain.CommentType;
import dotori.guidely.domain.comment.domain.TextCommentContent;
import dotori.guidely.domain.comment.domain.VoiceCommentContent;
import dotori.guidely.domain.comment.dto.CommentDto;
import dotori.guidely.domain.comment.dto.request.CommentRequestDto;
import dotori.guidely.domain.comment.dto.response.CommentResponseDto;
import dotori.guidely.domain.comment.repository.CommentRepository;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    // 텍스트 댓글 생성
    @Transactional
    public CommentDto creatTextComment(CommentRequestDto request, Long postId, Long userId) {
        // TODO: Add postException
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);

        // TODO: Add userException
        User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        // 텍스트 댓글 내용
        TextCommentContent textContent = TextCommentContent.builder()
                .text(request.getContent())
                .build();

        Comment textComment = Comment.builder()
                .type(CommentType.TEXT)
                .post(post)
                .user(user)
                .content(textContent)
                .build();

        commentRepository.save(textComment);


        return modelMapper.map(textComment, CommentDto.class);
    }

    // 음성 댓글 생성
    @Transactional
    public CommentDto creatVoiceComment(CommentRequestDto request, Long postId, Long userId) {
        // TODO: Add postException
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);

        // TODO: Add userException
        User user = userRepository.findById(userId)
                .orElseThrow(RuntimeException::new);

        // 음성 댓글 내용
        VoiceCommentContent voiceContent = VoiceCommentContent.builder()
                .voiceUrl(request.getContent())
                .build();

        Comment voiceComment = Comment.builder()
                .type(CommentType.VOICE)
                .post(post)
                .user(user)
                .content(voiceContent)
                .build();

        commentRepository.save(voiceComment);

        return modelMapper.map(voiceComment, CommentDto.class);
    }

    // postId에 해당하는 게시글의 모든 댓글 조회
    public List<CommentResponseDto> findAllByPostId(Long postId) {
        // TODO: Add Post Exception Handler
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(RuntimeException::new);

        List<Comment> comments = post.getComments();

        List<CommentResponseDto> responses = new ArrayList<>();
        for (Comment comment : comments) {
            responses.add(CommentResponseDto.builder()
                    .nickname(comment.getUser().getNickname())
                    .type(comment.getType())
                    .content(comment.getContent())
                    .build());
        }

        return responses;
    }

    @Transactional
    public CommentResponseDto modifyComment(CommentRequestDto request, Long commentId) {
        // TODO: Add Comment Exception Handler
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(RuntimeException::new);

        Long contentId = comment.getContent().getCommentContentId();
        if (request.getType() == CommentType.TEXT) {
            TextCommentContent textContent = commentRepository.findTextContentByCommentContentId(contentId);
            textContent.update(request.getContent());
            comment.update(textContent);
        }
        else {
            VoiceCommentContent voiceContent = commentRepository.findVoiceContentByCommentContentId(contentId);
            voiceContent.update(request.getContent());
            comment.update(voiceContent);
        }

        CommentResponseDto response = CommentResponseDto.builder()
                .nickname(comment.getUser().getNickname())
                .type(comment.getType())
                .content(comment.getContent())
                .build();

        return response;
    }

    // commentId에 해당하는 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
