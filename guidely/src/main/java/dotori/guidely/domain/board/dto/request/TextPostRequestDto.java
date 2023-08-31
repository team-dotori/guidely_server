package dotori.guidely.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextPostRequestDto {
    private String title;
    private String content;
}
