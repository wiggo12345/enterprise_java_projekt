
package ax.ha.it.cd.ruwich;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name= "observations")
@Getter
@Setter
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(views.WithObservation.class)
    private Integer id;

    @Getter


    @JsonView(views.WithObservation.class)
    private Date date;


    @JsonView(views.WithObservation.class)
    private String location;

    @JsonView(views.WithObservation.class)
    private String description;



    @ManyToOne
    @JoinColumn(name = "anomaly_id")
    @JsonView(views.WithObservation.class)
    @JsonBackReference
    private Anomaly anomaly;
    //A comment

}


