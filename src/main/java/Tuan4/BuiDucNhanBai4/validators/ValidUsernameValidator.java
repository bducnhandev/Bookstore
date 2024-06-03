package Tuan4.BuiDucNhanBai4.validators;

import Tuan4.BuiDucNhanBai4.services.UserService;
import Tuan4.BuiDucNhanBai4.validators.annotations.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext
            context) {
        return userService.findByUsername(username).isEmpty();
    }
}
