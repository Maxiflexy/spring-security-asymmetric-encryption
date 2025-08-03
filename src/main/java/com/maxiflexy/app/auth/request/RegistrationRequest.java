package com.maxiflexy.app.auth.request;

import com.maxiflexy.app.validation.NonDisposableEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequest {

    @NotBlank(message = "VALIDATION.REGISTRATION.FIRST_NAME.BLANK")
    @Size(
            min = 1,
            max = 50,
            message = "VALIDATION.REGISTRATION.FIRST_NAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.FIRST_NAME.PATTERN"
    )
    @Schema(example = "Maxiflexy")
    String firstName;

    @NotBlank(message = "VALIDATION.REGISTRATION.LAST_NAME.BLANK")
    @Size(
            min = 1,
            max = 50,
            message = "VALIDATION.REGISTRATION.LAST_NAME.SIZE"
    )
    @Pattern(
            regexp = "^[\\p{L} '-]+$",
            message = "VALIDATION.REGISTRATION.LAST_NAME.PATTERN"
    )
    @Schema(example = "Onyekachi")
    String lastName;

    @NotBlank(message = "VALIDATION.REGISTRATION.EMAIL.BLANK")
    @Email(message = "VALIDATION.REGISTRATION.EMAIL.FORMAT")
    @NonDisposableEmail(message = "VALIDATION.REGISTRATION.EMAIL.DISPOSABLE")
    @Schema(example = "maxiflexy@mail.com")
    String email;

    @NotBlank(message = "VALIDATION.REGISTRATION.PHONE.BLANK")
    @Pattern(regexp = "^\\+?[0-9]\\d{10,14}$",
            message = "VALIDATION.REGISTRATION.PHONE.FORMAT"
    )
    @Schema(example = "+2348187626932")
    String phoneNumber;

    @NotBlank(message = "VALIDATION.REGISTRATION.PASSWORD.BLANK")
    @Size(min = 8,
            max = 72,
            message = "VALIDATION.REGISTRATION.PASSWORD.SIZE"
    )
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).*$",
            message = "VALIDATION.REGISTRATION.PASSWORD.WEAK"
    )
    @Schema(example = "pAssword1!_")
    String password;

    @NotBlank(message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.BLANK")
    @Size(min = 8,
            max = 72,
            message = "VALIDATION.REGISTRATION.CONFIRM_PASSWORD.SIZE"
    )
    @Schema(example = "pAssword1!_")
    String confirmPassword;


}