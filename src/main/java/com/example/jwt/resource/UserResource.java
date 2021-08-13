package com.example.jwt.resource;

import com.example.jwt.model.UserModel;
import com.example.jwt.repository.UserRepository;
import com.example.jwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserResource {

    private UserService userService;
    private UserRepository userRepository;

    @GetMapping
    public List<UserModel> findAll() {
        return this.userRepository.findAll();
    }

    @PostMapping
    public UserModel save(@RequestBody UserModel user) {
        return this.userService.save(user);
    }

}
