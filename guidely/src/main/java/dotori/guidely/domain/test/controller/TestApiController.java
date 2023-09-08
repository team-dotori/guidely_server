package dotori.guidely.domain.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "배포 테스트 API", description = "CI/CD 테스트를 위한 api")
@RestController
@RequestMapping("/api/tests")
public class TestApiController {

    @Operation(summary = "배포 테스트")
    @GetMapping("/hello")
    public HelloResponse getHello(){
        return new HelloResponse(3L, "Hello World NEW VERSION 9/7");
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