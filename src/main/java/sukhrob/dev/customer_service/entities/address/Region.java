package sukhrob.dev.customer_service.entities.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import sukhrob.dev.customer_service.entities.template.AbsEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "update regions set deleted = true where id = ?")
@SQLRestriction(value = "deleted=false")
@Entity(name = "regions")
public class Region extends AbsEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long countryId;

}
