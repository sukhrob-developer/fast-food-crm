package sukhrob.dev.customer_service.payload;

public record CategoryResponseDTO(
        String name,
        Long parentCategoryId
) {
}
