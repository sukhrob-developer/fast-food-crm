package sukhrob.dev.customer_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sukhrob.dev.customer_service.payload.CategoryRequestDTO;
import sukhrob.dev.customer_service.payload.CategoryResponseDTO;
import sukhrob.dev.customer_service.services.CategoryService;
import sukhrob.dev.customer_service.utils.AppConstant;

import java.util.List;

@RestController
@RequestMapping(AppConstant.CATEGORY_CONTROLLER)
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping("/{id}")
    CategoryResponseDTO get(@PathVariable(name = "id") Long id) {
        return categoryService.get(id);
    }

    @GetMapping()
    List<CategoryResponseDTO> getAll() {
        return categoryService.getAll();
    }

    @PostMapping()
    CategoryResponseDTO add(@RequestBody CategoryRequestDTO categoryReqDTO) {
        return categoryService.add(categoryReqDTO);
    }

    @PutMapping("{id}")
    CategoryResponseDTO update(
            @PathVariable(name = "id") Long id,
            @RequestBody CategoryRequestDTO categoryReqDTO
    ) {
        return categoryService.update(id, categoryReqDTO);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        return categoryService.delete(id);
    }

}
