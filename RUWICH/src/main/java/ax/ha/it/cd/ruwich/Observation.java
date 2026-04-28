
package ax.ha.it.cd.ruwich;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JoinColumn(name = "anomaly_id")
    @JsonBackReference
    private Anomaly anomaly;
    //A comment

}


