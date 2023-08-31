package dotori.guidely.domain.board.service;

import dotori.guidely.domain.board.domain.TextPost;
import dotori.guidely.domain.board.dto.TextPostDto;
import dotori.guidely.domain.board.dto.request.TextPostRequestDto;
import dotori.guidely.domain.board.dto.response.TextPostResponseDto;
import dotori.guidely.domain.board.repository.TextPostRepository;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final TextPostRepository textPostRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // 단일 텍스트 게시글 생성
    @Transactional
    public Long createTextPost(TextPostDto request, Long userId) {
        // TODO: userException 추가
        User user = userRepository.findByUserId(userId)
                .orElseThrow(RuntimeException::new);

        request.setUserId(userId);
        request.setLikeCount(0);
        log.info("CreateTextPost create() 실행");

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TextPost textPost= modelMapper.map(request, TextPost.class);

        textPostRepository.save(textPost);

        return textPost.getUserId();
    }

    // 게시글 ID로 게시글 조회
    @Transactional
    public TextPostDto findByTextPostId(Long textPostId) {
        TextPost textPost = textPostRepository.findByTextPostId(textPostId)
                .orElseThrow(RuntimeException::new);

        TextPostDto textPostDto = modelMapper.map(textPost, TextPostDto.class);
        textPostDto.setNickname(userRepository.findByUserId(textPost.getUserId())
                .orElseThrow(RuntimeException::new).getNickname());

        return textPostDto;
    }

    // 사용자 ID로 해당 사용자의 모든 텍스트 게시글 조회
    @Transactional
    public List<TextPostDto> findAllByUserId(Long userId) {
        List<TextPost> textPosts = textPostRepository.findAllByUserId(userId);

        List<TextPostDto> textPostDtos = new ArrayList<>();
        for (TextPost textPost : textPosts) {
            textPostDtos.add(modelMapper.map(textPost, TextPostDto.class));
        }

        return textPostDtos;
    }

    @Transactional
    public Long deleteTextPost(Long textPostId) {
        textPostRepository.deleteByTextPostId(textPostId);

        return textPostId;
    }

    @Transactional
    public TextPostDto modifyTextPost(Long textPostId, TextPostDto textPostDto) {
        TextPost textPost = textPostRepository.findByTextPostId(textPostId)
                .orElseThrow(RuntimeException::new);

        textPost.update(textPostDto);

        TextPostDto returnValue = modelMapper.map(textPost, TextPostDto.class);
        returnValue.setNickname(userRepository.findByUserId(textPost.getUserId())
                .orElseThrow(RuntimeException::new).getNickname());
        return returnValue;
    }



}
