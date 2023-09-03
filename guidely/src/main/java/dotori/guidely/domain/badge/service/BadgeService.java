package dotori.guidely.domain.badge.service;

import dotori.guidely.domain.badge.domain.Badge;
import dotori.guidely.domain.badge.repository.BadgeRepository;
import dotori.guidely.domain.user.domain.User;
import dotori.guidely.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;
    private final UserService userService;

    private final ModelMapper modelMapper;

    public List<Badge> list(){
        return badgeRepository.findAll();
    }
//    public List<Badge> findByUserId(long id){
//        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        UserDto userDto = userService.findByUserId(id);
//        List<Badge> badges = badgeRepository.findByUser(modelMapper.map(userDto, User.class));
//        return badges;
//    }
    public void reset(long id){
        User user = userService.findByid(id);
        for(int i = 0 ; i<10 ; i++){
            Badge badge = Badge.builder()
                    .id(i)
                    .level(1) // 0
                    .state(0) //비활성화
                    .kingBadge(0) // False
                    .build();
            user.addBadge(badge);
            badge.setUser(user);
            badgeRepository.save(badge);
        }
    }

}
