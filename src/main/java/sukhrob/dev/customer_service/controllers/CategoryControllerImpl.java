package sukhrob.dev.customer_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import sukhrob.dev.customer_service.payload.CategoryReqDTO;
import sukhrob.dev.customer_service.payload.CategoryResDTO;
import sukhrob.dev.customer_service.services.CategoryService;

import java.util.List;

@RestController
public class CategoryControllerImpl implements CategoryController {

    private CategoryService categoryService;

    @Override
    public ResponseEntity<CategoryResDTO> get(Long id) {
        return categoryService.get(id);
    }

    @Override
    public ResponseEntity<List<CategoryResDTO>> getAll() {
        return categoryService.getAll();
    }

    @Override
    public ResponseEntity<CategoryResDTO> add(CategoryReqDTO categoryReqDTO) {
        return categoryService.add(categoryReqDTO);
    }

    @Override
    public ResponseEntity<CategoryResDTO> update(Long id, CategoryReqDTO categoryReqDTO) {
        return categoryService.update(id, categoryReqDTO);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return categoryService.delete(id);
    }
}
