package ax.ha.it.cd.ruwich;
import jakarta.persistence.*;

@Entity
@Table(name= "Anomalies")
public class Anomalies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


}
