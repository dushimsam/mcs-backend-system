package com.example.mount_carmel_school.service;

import com.example.mount_carmel_school.dto.DeleteResponseDto;
import com.example.mount_carmel_school.dto.UserDto.PaginatedUserResponse;
import com.example.mount_carmel_school.dto.UserDto.UserDtoGet;
import com.example.mount_carmel_school.dto.UserDto.UserDtoPost;
import com.example.mount_carmel_school.dto.auth_dto.PasswordChangeRequest;
import com.example.mount_carmel_school.dto.auth_dto.PasswordChangeResponse;
import com.example.mount_carmel_school.exception.ApiRequestException;
import com.example.mount_carmel_school.exception.NotFoundException;
import com.example.mount_carmel_school.model.User;
import com.example.mount_carmel_school.repository.UserRepository;
import com.example.mount_carmel_school.storage.FileSystemStorageService;
import com.example.mount_carmel_school.validation.EmailValidator;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailValidator emailValidator;


    public UserDtoGet add(UserDtoPost userDtoPost)  {
        User userExists = userRepository.findByEmail(userDtoPost.getEmail());
        User userExistsUsername = userRepository.findByUserName(userDtoPost.getUserName());
        boolean isValidEmail = emailValidator.
                test(userDtoPost.getEmail());

        if (!isValidEmail) {
            throw new ApiRequestException("email not valid");
        }

        if (userExists != null) {
            throw new ApiRequestException("email already taken");
        }
        if (userExistsUsername != null) {
            throw new ApiRequestException("username already taken");
        }

        String encodedPassword = bCryptPasswordEncoder
                .encode(userDtoPost.getPassword());
                User user = new User();
                BeanUtils.copyProperties(userDtoPost,user);
                user.setPassword(encodedPassword);
                user.setUserName(userDtoPost.getUserName());
                userRepository.save(user);
                return new UserDtoGet(user);
    }

    public List<UserDtoGet> getAll() {
        List<User> users =  userRepository.findAll();
        return traversalCopy(users);
    }

    public PaginatedUserResponse getAllPaginated(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return PaginatedUserResponse.builder()
                .numberOfItems(users.getTotalElements()).numberOfPages(users.getTotalPages())
                .users(traversalCopy(users.getContent()))
                .build();
    }

    public PaginatedUserResponse search(String key,Pageable pageable) {
        Page<User> users = userRepository.findByEmailContainingAndFirstNameContainingAndLastNameContaining(key,key,key,pageable);
        return PaginatedUserResponse.builder()
                .numberOfItems(users.getTotalElements()).numberOfPages(users.getTotalPages())
                .users(traversalCopy(users.getContent()))
                .build();
    }

    public UserDtoGet get(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User"));
        if(user != null)
        {
            return new UserDtoGet(user);
        }else{
            throw new ApiRequestException("User Not Found");
        }
    }

    public UserDtoGet confirmReject(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User"));
        user.setIsConfirmed(true);
        return new UserDtoGet(userRepository.save(user));
    }

    public DeleteResponseDto delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User"));
        userRepository.delete(user);
        return new DeleteResponseDto(new UserDtoGet(user));
    }
    public UserDtoGet getByUsername(String username)
    {
        return new UserDtoGet(userRepository.findByUserName(username));
    }

    public UserDtoGet getByEmail(String email)
    {
        return new UserDtoGet(userRepository.findByEmail(email));
    }

    public ResponseEntity<Object> changeProfile(MultipartFile file, Long userId){
        User user = userRepository.findById(userId).get();
        if(user != null)
        {
            if(!user.getProfile().equals(""))
            {
                if(fileSystemStorageService.deleteFile(user.getProfile())){
                    System.out.println("Deleted successfully");
                }
            }
            String fileName = fileSystemStorageService.saveUserFile(file);
            user.setProfile(fileName);
            userRepository.save(user);
            return  new ResponseEntity<Object>("Profile Updated Successfully", HttpStatus.OK);
        }else{
            throw  new ApiRequestException("User not Found");
        }

    }

    public UserDtoGet update(UserDtoPost userDtoPost,Long userId)
    {
        User user =userRepository.findById(userId).orElseThrow(()->new NotFoundException("User"));

        if (userRepository.findByEmail(userDtoPost.getEmail()) != null) {
            throw new ApiRequestException("email already taken");
        }
        if (userRepository.findByUserName(userDtoPost.getUserName()) != null) {
            throw new ApiRequestException("username already taken");
        }
        if(userDtoPost.getProfile() == null || userDtoPost.getProfile().equals(""))
        {
            throw  new ApiRequestException("Profile can not be null");
        }

        boolean isValidEmail = emailValidator. test(userDtoPost.getEmail());

        if (!isValidEmail) {
            throw new ApiRequestException("email not valid");
        }

        BeanUtils.copyProperties(userDtoPost,user,"password");
        user.setUserName(userDtoPost.getUserName());
        return new UserDtoGet(user);
    }


    public PasswordChangeResponse changePassword(Long userId, PasswordChangeRequest passwordChangeRequest)
    {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User"));

        String curr_password = bCryptPasswordEncoder.encode(passwordChangeRequest.getCurrent_password());
        if(curr_password.equals(user.getPassword()))
        {
            user.setPassword(bCryptPasswordEncoder.encode(passwordChangeRequest.getNew_password()));
        }
       return new PasswordChangeResponse("PASSWORD CHANGED SUCCESSFULLY",new UserDtoGet(user));
    }

    public DeleteResponseDto disableUnDisable(Long userId)
    {
        User user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User"));
        user.setIsLocked(!user.getIsLocked());
        return new DeleteResponseDto(user.getIsLocked() ? "ACCOUNT LOCKED SUCCESSFULLY":"ACCOUNT UNLOCKED SUCCESSFULLY",new UserDtoGet(userRepository.save(user)));
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User userByName = userRepository.findByUserName(usernameOrEmail);
        User userByEmail = userRepository.findByEmail(usernameOrEmail);

        if(userByEmail != null)
        {
            return new org.springframework.security.core.userdetails.User(userByEmail.getUsername(), userByEmail.getPassword(), new ArrayList<>());
        }else if (userByName != null)
        {
            return new org.springframework.security.core.userdetails.User(userByName.getUsername(), userByName.getPassword(), new ArrayList<>());
        }else{
            throw new ApiRequestException("Invalid Email or Password");
        }

    }

    public List<UserDtoGet> traversalCopy(List<User> users)
    {
        List<UserDtoGet>  usersDto = new ArrayList<>();
        for (User item : users){
            usersDto.add(new UserDtoGet(item));
        }
        return usersDto;
    }
}


