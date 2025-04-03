package org.iot.patterns.controller;

import lombok.RequiredArgsConstructor;
import org.iot.patterns.entity.Subscription;
import org.iot.patterns.entity.User;
import org.iot.patterns.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserServiceController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }
}
