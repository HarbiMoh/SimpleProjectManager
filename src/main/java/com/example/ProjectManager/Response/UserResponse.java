package com.example.ProjectManager.Response;

import com.example.ProjectManager.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    
    private Long id;
    private String name;
    private String email;
    private String username;

    public static UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .username(user.getUsername())
        .build();
       }
}
