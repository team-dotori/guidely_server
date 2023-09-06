package dotori.guidely.domain.badge.controller;

import dotori.guidely.domain.badge.service.BadgeService;
import dotori.guidely.domain.user.service.UserService;
import dotori.guidely.global.utils.jwt.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/badge")
@CrossOrigin(origins = "*")
public class BadgeController {
    private final BadgeService badgeService;
    private final AuthTokensGenerator authTokensGenerator;

    private final UserService userService;

    @GetMapping
    public void createTextPost(@RequestHeader(value = "accessToken") String accessToken) {
        Long userId = authTokensGenerator.extractUserId(accessToken);
        badgeService.reset(userService.findByid(userId));
    }
}