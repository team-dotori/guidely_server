package dotori.guidely.domain.user.dto;

import dotori.guidely.domain.user.domain.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeDto {
    private UserType type;
}
