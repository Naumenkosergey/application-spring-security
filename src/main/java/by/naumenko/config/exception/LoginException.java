package by.naumenko.config.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class LoginException {

    private final String message;

    private String username;

    private String password;


}
