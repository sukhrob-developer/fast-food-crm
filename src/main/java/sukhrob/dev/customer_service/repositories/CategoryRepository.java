package sukhrob.dev.customer_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sukhrob.dev.customer_service.entities.product.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
