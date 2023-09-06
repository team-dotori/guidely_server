package dotori.guidely.domain.badge.scheduler;

import dotori.guidely.domain.badge.domain.Badge;
import dotori.guidely.domain.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CollectBadge {
    private User user;
    private int num ; //기준 number
    private int count;
    private int idx;


    public CollectBadge(User user,int num, int count, int idx) {
        this.user = user;
        this.num = num;
        this.count = count;
        this.idx = idx;
    }

    //    @Scheduled(fixedRate = 1000) //1초마다 실행
    public void detectAndExecute(){ //신고 갯수 , badge index
        int currentLevel = user.getBadges().get(idx).getLevel();
        if(currentLevel*num == count){ //신고 갯수가 목표 갯수에 달성하면
            Badge badge = user.getBadges().get(idx);
            if(badge.getState()==0){ //처음으로 뱃지를 땄다면
                badge.firstUpdate();
            }
            else{
                badge.update();
            }

        }
    }
    public void setUser(User user){
        this.user = user;
    }
}
