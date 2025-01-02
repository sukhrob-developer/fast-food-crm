package sukhrob.dev.customer_service.entities.address;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import sukhrob.dev.customer_service.entities.template.AbsEntity;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "countries")
@SQLRestriction(value = "deleted=false")
@SQLDelete(sql = "update countries set deleted = true where id = ?")
public class Country extends AbsEntity {

    @Column(nullable = false, unique = true)
    private String name;

}
