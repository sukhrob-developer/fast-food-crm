package sukhrob.dev.customer_service.services;

import org.springframework.http.ResponseEntity;
import sukhrob.dev.customer_service.payload.CategoryReqDTO;
import sukhrob.dev.customer_service.payload.CategoryResDTO;

import java.util.List;

public interface CategoryService {

    ResponseEntity<CategoryResDTO> get(Long id);

    ResponseEntity<List<CategoryResDTO>> getAll();

    ResponseEntity<CategoryResDTO> add(CategoryReqDTO categoryReqDTO);

    ResponseEntity<CategoryResDTO> update(Long id, CategoryReqDTO categoryReqDTO);

    ResponseEntity<?> delete(Long id);

}
