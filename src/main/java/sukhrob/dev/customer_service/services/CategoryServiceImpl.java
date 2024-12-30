package sukhrob.dev.customer_service.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sukhrob.dev.customer_service.entities.product.Category;
import sukhrob.dev.customer_service.payload.CategoryReqDTO;
import sukhrob.dev.customer_service.payload.CategoryResDTO;
import sukhrob.dev.customer_service.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<CategoryResDTO> get(Long id) {
        Category category = findById(id);
        return ResponseEntity.ok(convertEntityToDTO(category));
    }

    @Override
    public ResponseEntity<List<CategoryResDTO>> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResDTO> categoryResDTOS = categories.stream()
                .map(this::convertEntityToDTO)
                .toList();
        return ResponseEntity.ok(categoryResDTOS);
    }

    @Override
    public ResponseEntity<CategoryResDTO> add(CategoryReqDTO categoryReqDTO) {
        boolean existsByName = categoryRepository.existsByName(categoryReqDTO.getName());
        if (existsByName) throw new EntityExistsException("This category already exists!");

        if (categoryReqDTO.getParentCategoryId() != null) {
            findById(categoryReqDTO.getParentCategoryId());
        }

        Category category = new Category(
                categoryReqDTO.getName(),
                categoryReqDTO.getParentCategoryId()
        );
        return ResponseEntity.ok(convertEntityToDTO(category));
    }

    @Override
    public ResponseEntity<CategoryResDTO> update(Long id, CategoryReqDTO categoryReqDTO) {
        boolean existsByNameAndIdNot = categoryRepository.existsByNameAndIdNot(
                categoryReqDTO.getName(),
                id
        );
        if (existsByNameAndIdNot) {
            throw new EntityExistsException("Category with this name already exists!");
        }

        if (categoryReqDTO.getParentCategoryId() != null) {
            findById(categoryReqDTO.getParentCategoryId());
        }

        Category category = findById(id);
        category.setName(categoryReqDTO.getName());
        category.setParentCategoryId(categoryReqDTO.getParentCategoryId());
        Category updatedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(convertEntityToDTO(updatedCategory));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
        return ResponseEntity.ok("Successfully deleted!");
    }

    private CategoryResDTO convertEntityToDTO(Category category) {
        return new CategoryResDTO(
                category.getName(),
                category.getParentCategoryId()
        );
    }

    private Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Entity with the id " + id + " is not found!"
                )
        );
    }

}
