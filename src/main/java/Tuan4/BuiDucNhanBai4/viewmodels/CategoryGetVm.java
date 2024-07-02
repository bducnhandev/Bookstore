package Tuan4.BuiDucNhanBai4.viewmodels;

import Tuan4.BuiDucNhanBai4.entities.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoryGetVm(Long id, String name) {
    public static CategoryGetVm from(@NotNull Category category) {
        return CategoryGetVm.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
