package ax.ha.it.cd.ruwich;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name= "anomalies")
public class Anomaly {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "anomaly", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Observation> observations;

    private Date




    @Setter
    @Getter
    private String name;
    private String description;
    private Classification classification;


}
