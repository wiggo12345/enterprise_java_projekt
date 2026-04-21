
package ax.ha.it.cd.ruwich;
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
    private Integer id;

    @Getter

    private Date date;

    private String location;

    private String description;



    @ManyToOne
    @JoinColumn(name = "anomalyId")
    private Anomaly anomaly;
    //A comment

}


