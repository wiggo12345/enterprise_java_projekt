package ax.ha.it.cd.ruwich;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name= "anomalies")
@Getter
@Setter
public class Anomaly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "anomaly", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Observation> observations;








    private String name;

    private String description;

    private String classification;

}
