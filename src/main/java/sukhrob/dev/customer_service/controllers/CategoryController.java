package sukhrob.dev.customer_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sukhrob.dev.customer_service.payload.CategoryReqDTO;
import sukhrob.dev.customer_service.payload.CategoryResDTO;
import sukhrob.dev.customer_service.utils.AppConstant;

import java.util.List;

@RequestMapping(AppConstant.CATEGORY_CONTROLLER)
public interface CategoryController {

    @GetMapping(AppConstant.GET + "/{id}")
    ResponseEntity<CategoryResDTO> get(@PathVariable(name = "id") Long id);

    @GetMapping(AppConstant.GET)
    ResponseEntity<List<CategoryResDTO>> getAll();

    @PostMapping(AppConstant.ADD)
    ResponseEntity<CategoryResDTO> add(@RequestBody CategoryReqDTO categoryReqDTO);

    @PutMapping(AppConstant.UPDATE + "{id}")
    ResponseEntity<CategoryResDTO> update(@PathVariable(name = "id") Long id,
                                          @RequestBody CategoryReqDTO categoryReqDTO);

    @DeleteMapping(AppConstant.DELETE + "{id}")
    ResponseEntity<?> delete(@PathVariable(name = "id") Long id);

}
