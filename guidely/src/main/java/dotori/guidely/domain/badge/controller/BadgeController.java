package dotori.guidely.domain.badge.controller;

import dotori.guidely.domain.badge.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/badge")
@CrossOrigin(origins = "*")
public class BadgeController {
    private final BadgeService badgeService;
//    @GetMapping
//    public void reset(@RequestHeader(value = "accessToken") String accessToken){
//        badgeService.reset()
//    }
}
