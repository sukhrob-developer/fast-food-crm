package sukhrob.dev.customer_service.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import sukhrob.dev.customer_service.entities.product.Category;
import sukhrob.dev.customer_service.mappers.CategoryMapper;
import sukhrob.dev.customer_service.payload.CategoryRequestDTO;
import sukhrob.dev.customer_service.repositories.CategoryRepository;

@WebMvcTest
class CategoryServiceImplTest {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    private CategoryServiceImpl testService;

    @Test
    void get() {
        CategoryRequestDTO categoryRequestDTO = new CategoryRequestDTO("Pizza", null);
        Category category = categoryRepository.save(categoryMapper.mapDTOToEntity(categoryRequestDTO));

    }

    @Test
    void getAll() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}