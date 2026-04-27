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
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Betyder att databasen ansvrar för att skapa ett unikt id automatiskt
    private Integer id;                         //GenerationType.IDENTITY betyder att databasen hanterar autoinkrement
    @OneToMany(mappedBy = "anomaly", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Observation> observations;



    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Classification classification;

}
