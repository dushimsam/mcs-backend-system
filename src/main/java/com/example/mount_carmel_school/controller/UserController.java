package com.example.mount_carmel_school.controller;


import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.dto.UserDto.UserDtoPost;
import com.example.mount_carmel_school.dto.auth_dto.AuthRequest;
import com.example.mount_carmel_school.dto.auth_dto.AuthResponse;
import com.example.mount_carmel_school.dto.auth_dto.PasswordChangeRequest;
import com.example.mount_carmel_school.dto.auth_dto.PasswordChangeResponse;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.service.UserService;
import com.example.mount_carmel_school.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<List<UserDtoGet>> getAll() {
        return new ResponseEntity<List<UserDtoGet>>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDtoGet> add(@RequestBody UserDtoPost userDtoPost){
        return new ResponseEntity<UserDtoGet>(userService.add(userDtoPost), HttpStatus.CREATED) ;
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<UserDtoGet> get(
            @PathVariable("id") Long id) {
        return   new ResponseEntity<UserDtoGet>(userService.get(id),HttpStatus.OK);
    }

    @GetMapping(path = "/username/{userName}")
    public ResponseEntity<UserDtoGet> getByUserName(
            @PathVariable("userName") String userName) {
        return   new ResponseEntity<UserDtoGet>(userService.getByUsername(userName),HttpStatus.OK);
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<UserDtoGet> getByEmail(
            @PathVariable("email") String email) {
        return   new ResponseEntity<UserDtoGet>(userService.getByEmail(email),HttpStatus.OK);
    }


    @PutMapping(path = "{id}")
    public ResponseEntity<UserDtoGet> update(
            @PathVariable("id") Long id,@RequestBody UserDtoPost userDtoPost) {
        return   new ResponseEntity<UserDtoGet>(userService.update(userDtoPost,id),HttpStatus.OK);
    }

    @PostMapping(path="/auth/login")
    public ResponseEntity<AuthResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid userName/password");
        }


        UserDtoGet user = userService.getByUsername(authRequest.getLogin());

        StringBuilder builder = new StringBuilder();
        builder.append("{\"id\" :");
        builder.append(user.getId());
        builder.append(", \"firstName\" :");
        builder.append(user.getFirstName());
        builder.append(", \"lastName\" :");
        builder.append(user.getLastName());
        builder.append(", \"category\" :");
        builder.append(user.getCategory());
        builder.append(", \"email\" :");
        builder.append(user.getEmail());
        builder.append("}");

        return new ResponseEntity<AuthResponse>(new AuthResponse(jwtUtil.generateToken(user)),HttpStatus.OK) ;
    }

    @PutMapping("change-profile/{userId}")
    public ResponseEntity<Object> changeProfile(@RequestParam("File") MultipartFile file, @PathVariable("userId") Long userId)
    {
        return userService.changeProfile(file,userId);
    }

    @PutMapping(path="change-password/{userId}")
    public ResponseEntity<PasswordChangeResponse> add(@PathVariable("userId") Long userId, @RequestBody PasswordChangeRequest passwordChangeRequest){
        return new ResponseEntity<PasswordChangeResponse>(userService.changePassword(userId,passwordChangeRequest), HttpStatus.OK) ;
    }

    @PutMapping("toggle-disable/{userId}")
    public ResponseEntity<DeleteResponseDto> disableUnDisable(@PathVariable("userId") Long id) {
        return new ResponseEntity<DeleteResponseDto>(userService.disableUnDisable(id), HttpStatus.OK);
    }
}
