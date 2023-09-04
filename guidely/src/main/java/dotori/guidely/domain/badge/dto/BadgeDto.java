package dotori.guidely.domain.badge.dto;

import dotori.guidely.domain.badge.domain.Badge;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BadgeDto {
    private Long badgeId;
    private int state; //0 비활성 1 활성
    private int level;
    private int kingBadge ; //대표뱃지 여부 0-False 1- True
    private LocalDateTime collectDate;
    public Badge toEntity(){
        Badge badge = Badge.builder()
                .kingBadge(this.kingBadge)
                .id(this.badgeId)
                .state(this.state)
                .level(this.level)
                .build();
        return badge;
    }
    @Builder
    public BadgeDto(Long badgeId, int state, int level, int kingBadge, LocalDateTime collectDate) {
        this.badgeId = badgeId;
        this.state = state;
        this.level = level;
        this.kingBadge = kingBadge;
        this.collectDate = collectDate;
    }
}
