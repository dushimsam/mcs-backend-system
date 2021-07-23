package com.example.mount_carmel_school.controller;

import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.dto.UserDto.UserDtoPost;
import com.example.mount_carmel_school.service.UserService;
import com.example.mount_carmel_school.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @GetMapping
    public List<UserDtoGet> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public UserDtoGet add(@RequestBody UserDtoPost userDtoPost)  {
       return userService.add(userDtoPost);
    }

    @GetMapping(path = "{id}")
    public UserDtoGet get(
            @PathVariable("id") Long id) {
     return   userService.get(id);
    }

//    @PostMapping("/authenticate")
//    public AuthResponse generateToken(@RequestBody AuthRequest authRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
//            );
//        } catch (Exception ex) {
//            throw new Exception("Invalid userName/password");
//        }
//        return new AuthResponse(jwtUtil.generateToken(authRequest.getUserName()));
//    }

    @PutMapping("change-profile/{userId}")
    public ResponseEntity<Object> changeProfile(@RequestParam("File") MultipartFile file, @PathVariable("userId") Long userId)
    {
        return userService.changeProfile(file,userId);
    }
}
