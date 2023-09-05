package dotori.guidely.domain.badge.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dotori.guidely.domain.user.domain.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long badgeId;

    @NonNull
    private int state; //0 비활성 1 활성

    private int level;

    private int kingBadge ; //대표뱃지 여부 0-False 1- True

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime collectDate;

    @Builder
    public Badge( long id,@NonNull int state, int level, int kingBadge) {
        this.badgeId = id;
        this.state = state;
        this.level = level;
        this.kingBadge = kingBadge;
    }
    public void firstUpdate(){
        this.state = 1;
        this.collectDate = LocalDateTime.now();
        this.level +=1;
    }
    public void update(){
        this.level +=1;
        this.collectDate = LocalDateTime.now();
    }
    public void setUser(User user){
        this.user = user;
    }
}
