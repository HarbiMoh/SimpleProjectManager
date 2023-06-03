package com.example.ProjectManager.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignupRequest {
    
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z1-9]{1,50}$")
    private String username;
    @Size(min = 6)
    @Size(max = 32)
    private String password;
}
