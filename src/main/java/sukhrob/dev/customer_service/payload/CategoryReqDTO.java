package sukhrob.dev.customer_service.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryReqDTO {

    @NotNull(message = "Category name is a required field!")
    private String name;

    private Long parentCategoryId;

}
