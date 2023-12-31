package dotori.guidely.domain.badge.service;

import dotori.guidely.domain.badge.domain.Badge;
import dotori.guidely.domain.badge.repository.BadgeRepository;
import dotori.guidely.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeService {
    private final BadgeRepository badgeRepository;

    public List<Badge> list(){
        return badgeRepository.findAll();
    }
    @Transactional
    public User reset(User user){
        List<Badge> badges = new ArrayList<>();
        System.out.println(" reset start ");
        for(int i = 0 ; i<9 ; i++) {
            if(i ==0){
                Badge badge = Badge.builder() //default 뱃지
                        .level(1) // 0
                        .state(1) //활성화
                        .kingBadge(1) // True
                        .collectDate(LocalDateTime.now())
                        .build();
                badge.setUser(user);
                badgeRepository.save(badge);
                badges.add(badge);
                continue;
            }
            Badge badge = Badge.builder()
                    .level(1) // 0
                    .state(0) //비활성화
                    .kingBadge(0) // False
                    .build();
            badge.setUser(user);
            badgeRepository.save(badge);
            badges.add(badge);
        }
        user.addBadge(badges);
        return user;
    }

}
