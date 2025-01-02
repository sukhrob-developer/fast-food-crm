package sukhrob.dev.customer_service.services;

import org.springframework.http.ResponseEntity;
import sukhrob.dev.customer_service.payload.CategoryRequestDTO;
import sukhrob.dev.customer_service.payload.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO get(Long id);

    List<CategoryResponseDTO> getAll();

    CategoryResponseDTO add(CategoryRequestDTO categoryReqDTO);

    CategoryResponseDTO update(Long id, CategoryRequestDTO categoryReqDTO);

    ResponseEntity<?> delete(Long id);

}
