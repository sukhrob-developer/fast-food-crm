package sukhrob.dev.customer_service.entities.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import sukhrob.dev.customer_service.entities.template.AbsEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity(name = "categories")
@SQLDelete(sql = "update categories set deleted=true where id = ?")
@SQLRestriction(value = "deleted=false")
public class Category extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

    private Long parentCategoryId;

    public Category(String name) {
        this.name = name;
    }

}
