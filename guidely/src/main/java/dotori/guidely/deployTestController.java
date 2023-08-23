package dotori.guidely;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tests")
public class deployTestController {
    @GetMapping("/hello")
    public HelloResponse getHello(){
        return new HelloResponse(2L, "Hello World V21");
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static class HelloResponse{
        private Long id;
        private String content;
    }
}
