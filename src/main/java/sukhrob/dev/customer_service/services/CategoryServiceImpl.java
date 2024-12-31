package sukhrob.dev.customer_service.services;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sukhrob.dev.customer_service.entities.product.Category;
import sukhrob.dev.customer_service.mappers.CategoryMapper;
import sukhrob.dev.customer_service.payload.CategoryRequestDTO;
import sukhrob.dev.customer_service.payload.CategoryResponseDTO;
import sukhrob.dev.customer_service.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDTO get(Long id) {
        Category category = findById(id);
        return mapperEntityToDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::mapEntityToDTO)
                .toList();
    }

    @Override
    public CategoryResponseDTO add(CategoryRequestDTO categoryReqDTO) {
        boolean existsByName = categoryRepository.existsByName(categoryReqDTO.name());
        if (existsByName) throw new EntityExistsException("This category already exists!");

        if (categoryReqDTO.parentCategoryId() != null) {
            findById(categoryReqDTO.parentCategoryId());
        }

        Category category = categoryMapper.mapDTOToEntity(categoryReqDTO);
        categoryRepository.save(category);
        return mapperEntityToDTO(category);
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO categoryReqDTO) {
        boolean existsByNameAndIdNot = categoryRepository.existsByNameAndIdNot(
                categoryReqDTO.name(),
                id
        );
        if (existsByNameAndIdNot) {
            throw new EntityExistsException("Category with this name already exists!");
        }

        if (categoryReqDTO.parentCategoryId() != null) {
            findById(categoryReqDTO.parentCategoryId());
        }

        Category category = findById(id);
        category.setName(categoryReqDTO.name());
        category.setParentCategoryId(categoryReqDTO.parentCategoryId());
        Category updatedCategory = categoryRepository.save(category);
        return mapperEntityToDTO(updatedCategory);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
        return ResponseEntity.ok("Successfully deleted!");
    }

    private CategoryResponseDTO mapperEntityToDTO(Category category) {
        return categoryMapper.mapEntityToDTO(category);
    }

    private Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Entity with the id " + id + " is not found!"
                )
        );
    }

}
