package ax.ha.it.cd.ruwich;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @JsonManagedReference
    private List<Observation> observations;



    private String name;


    @Size(max = 400)
    @Column(length = 400)
    private String description;

    @Enumerated(EnumType.STRING)
    private Classification classification;

}
