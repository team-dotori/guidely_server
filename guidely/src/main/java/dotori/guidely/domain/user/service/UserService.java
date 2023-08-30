package dotori.guidely.domain.user.service;

import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.dto.UserDto;
import dotori.guidely.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserDto> findAll() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<User> users = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(modelMapper.map(user, UserDto.class));
        }

        return dtos;
    }

    public UserDto findByUserId(Long userId) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        /**
         * TODO
         * Exception Handler 정의
         */
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException());

        return modelMapper.map(user, UserDto.class);
    }

}
