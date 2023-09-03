package dotori.guidely.domain.badge.controller;

import dotori.guidely.domain.badge.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/badge")
@CrossOrigin(origins = "*")
public class BadgeController {
    private final BadgeService badgeService;
    @GetMapping("{id}") //임의. 회원가입시 badgeService reset함수 호출해야 함.
    public void resetBadge(@PathVariable long id){
        badgeService.reset(id);
    }
}
