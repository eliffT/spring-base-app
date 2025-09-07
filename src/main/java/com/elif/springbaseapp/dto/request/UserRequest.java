package com.elif.springbaseapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "firstname cannot be empty.")
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;

}
