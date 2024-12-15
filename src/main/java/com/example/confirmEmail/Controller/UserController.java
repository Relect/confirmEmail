package com.example.confirmEmail.Controller;

import com.example.confirmEmail.model.User;
import com.example.confirmEmail.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/confirm", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirm(@RequestParam("token") String token) {
        return userService.confirmEmail(token);
    }
}
