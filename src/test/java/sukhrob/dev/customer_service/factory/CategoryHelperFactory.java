package sukhrob.dev.customer_service.factory;

import sukhrob.dev.customer_service.entities.product.Category;

public class CategoryHelperFactory {

    public static Category defaultCategory() {
        return new Category("Pizza");
    }

    public static Category categoryWithParentId(Long id) {
        return new Category("Lavash", id);
    }

}
