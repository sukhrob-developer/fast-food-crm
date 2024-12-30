package sukhrob.dev.customer_service.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResDTO {

    private String name;

    private Long parentCategoryId;

}
