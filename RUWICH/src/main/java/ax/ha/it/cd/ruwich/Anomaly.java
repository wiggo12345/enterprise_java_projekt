package ax.ha.it.cd.ruwich;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import java.util.List;

@Entity
@Table(name= "anomalies")
@Getter
@Setter
public class Anomaly {

    @Id
    @JsonView(views.WithoutObservation.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Betyder att databasen ansvrar för att skapa ett unikt id automatiskt
    private Integer id;                         //GenerationType.IDENTITY betyder att databasen hanterar autoinkrement
    @OneToMany(mappedBy = "anomaly", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonView(views.WithObservation.class)
    private List<Observation> observations;


    @JsonView(views.WithoutObservation.class)
    private String name;


    @JsonView(views.WithoutObservation.class)
    @Size(max = 400)
    @Column(length = 400)
    private String description;

    @JsonView(views.WithoutObservation.class)
    @Enumerated(EnumType.STRING)
    private Classification classification;

}
