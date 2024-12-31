package sukhrob.dev.customer_service.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sukhrob.dev.customer_service.entities.product.Category;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository testRepository;

    @Test
    void existsByName() {
        Category category = new Category(
                "Pizza"
        );
        testRepository.save(category);

        boolean expectedValue = testRepository.existsByName("Pizza");

        assertThat(expectedValue).isTrue();
    }

    @Test
    void existsByNameAndIdNot() {

        Category category1 = new Category(
                "Drinks"
        );
        Category category2 = new Category(
                "Snacks"
        );

        Category savedCategory1 = testRepository.save(category1);
        Category savedCategory2 = testRepository.save(category2);

        boolean existsBySameNameButIdNot = testRepository.existsByNameAndIdNot(
                "Drinks",
                savedCategory2.getId()
        );
        boolean existsByDifferentNameButSameId = testRepository.existsByNameAndIdNot(
                "Snacks",
                savedCategory1.getId()
        );
        boolean existsBySameNameSameId = testRepository.existsByNameAndIdNot(
                "Drinks",
                savedCategory1.getId()
        );

        assertThat(existsBySameNameButIdNot).isTrue();
        assertThat(existsByDifferentNameButSameId).isTrue();
        assertThat(existsBySameNameSameId).isFalse();
    }

}