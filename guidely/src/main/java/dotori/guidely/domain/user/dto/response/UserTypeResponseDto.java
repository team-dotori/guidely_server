package dotori.guidely.domain.user.dto.response;

import dotori.guidely.domain.user.domain.UserType;
import lombok.Data;

@Data
public class UserTypeResponseDto {
    private UserType type;
}
