package sukhrob.dev.customer_service.payload;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(
        @NotNull String name,
        Long parentCategoryId
) {
}
