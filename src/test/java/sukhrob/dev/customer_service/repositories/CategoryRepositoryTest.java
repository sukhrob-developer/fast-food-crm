package sukhrob.dev.customer_service.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sukhrob.dev.customer_service.entities.product.Category;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository testRepository;

    @Test
    void Category_Repository_ExistsByName() {
        Category category = new Category(
                "Pizza"
        );
        testRepository.save(category);

        boolean expectedValue = testRepository.existsByName("Pizza");

        assertThat(expectedValue).isTrue();
    }

    @Test
    void CategoryRepository_ExistsByNameAndIdNot() {

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

    @Test
    void CategoryRepository_SaveAll_ReturnSavedCategory() {
        Category category1 = new Category("Pizza");

        Category savedCategory = testRepository.save(category1);

        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    void CategoryRepository_FindAll() {
        Category newCategory1 = Category.builder().name("Burgers").build();
        Category newCategory2 = Category.builder().name("Pizza").build();

        testRepository.save(newCategory1);
        testRepository.save(newCategory2);

        List<Category> categories = testRepository.findAll();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(2);
    }

    @Test
    void CategoryRepository_FindById_Return() {
        Category category = Category.builder().name("Lavash").build();

        Category savedCategory = testRepository.save(category);

        Category foundCategory = testRepository.findById(savedCategory.getId()).get();

        assertThat(foundCategory).isNotNull();
    }

    @Test
    void CategoryRepository_UpdateEntity() {
        Category category = Category.builder().name("Pizza").build();

        testRepository.save(category);

        Category savedCategory = testRepository.findById(category.getId()).get();
        savedCategory.setName("Pizza");

        Category updatedCategory = testRepository.save(savedCategory);
        assertThat(updatedCategory.getName()).isNotNull();
    }

    @Test
    void CategoryRepository_DeleteById() {
        Category category = Category.builder().name("Drinks").build();

        testRepository.save(category);

        testRepository.deleteById(category.getId());
        Optional<Category> optionalCategory = testRepository.findById(category.getId());
        assertThat(optionalCategory).isEmpty();
    }

}