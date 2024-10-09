package vn.vnist.project3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vnist.project3.dto.CreateUserDto;
import vn.vnist.project3.models.UserModel;
import vn.vnist.project3.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody CreateUserDto dto){
        return ResponseEntity.ok(userService.createUser(dto));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUser());
    }
}
