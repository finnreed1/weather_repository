package com.project.weatherApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserDto {
    @NotBlank(message = "Login should not be empty.")
    @Size(min = 3, max = 20, message = "Login should be between 3 and 20 characters.")
    @Pattern(
            regexp = "^[a-zA-Z0-9_.]+$",
            message = "The login contains invalid characters"
    )
    private String login;

    @NotBlank(message = "Password should not be empty.")
    @Size(min = 6, max = 20, message = "Password should be between 6 and 20 characters.")
    @Pattern(
            regexp = "^[a-zA-Z0-9_.]+$",
            message = "The password contains invalid characters"
    )
    private String password;


}
