package Tuan4.BuiDucNhanBai4.validators;

import Tuan4.BuiDucNhanBai4.entities.Category;
import Tuan4.BuiDucNhanBai4.validators.annotations.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category category,
                           ConstraintValidatorContext context) {
        return category != null && category.getId() != null;
    }
}

