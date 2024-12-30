package sukhrob.dev.customer_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import sukhrob.dev.customer_service.entities.product.Category;
import sukhrob.dev.customer_service.payload.CategoryRequestDTO;
import sukhrob.dev.customer_service.payload.CategoryResponseDTO;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponseDTO mapEntityToDTO(Category category);
    Category mapDTOToEntity(CategoryRequestDTO categoryRequestDTO);

}
