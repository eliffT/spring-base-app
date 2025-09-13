package com.elif.springbaseapp.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "Usename cannot be empty")
    private String username;

    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;

}
