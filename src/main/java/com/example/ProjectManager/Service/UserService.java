package com.example.ProjectManager.Service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ProjectManager.Entity.User;
import com.example.ProjectManager.Repositories.UserRepository;
import com.example.ProjectManager.Request.UserLoginRequest;
import com.example.ProjectManager.Request.UserSignupRequest;
import com.example.ProjectManager.Response.UserResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    
   private final UserRepository userRepository;
   
   public UserResponse signup(UserSignupRequest request){
    User user = User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .username(request.getUsername())
        .password(request.getPassword())
        .build();
    userRepository.save(user);
    return UserResponse.mapToUserResponse(user);
   }

   public ResponseEntity<UserResponse> login(UserLoginRequest request){
    Optional<User> user = userRepository.findByUsernameOrEmailAndPassword(request.getAccount_id(), request.getPassword());
    if(user.isPresent())
        return ResponseEntity.accepted().body(UserResponse.mapToUserResponse(user.get()));
    return ResponseEntity.notFound().build();
   }

}
