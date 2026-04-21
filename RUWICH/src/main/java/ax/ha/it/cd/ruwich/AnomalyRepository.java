package ax.ha.it.cd.ruwich;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnomalyRepository extends JpaRepository<Anomaly, Integer> {
    @Query("SELECT DISTINCT a FROM Anomaly a LEFT JOIN FETCH a.observations")
    List<Anomaly> findAllWithObservations();


}
