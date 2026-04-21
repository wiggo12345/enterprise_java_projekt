package ax.ha.it.cd.ruwich;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")
public class AnomalyController {
    private final AnomalyRepository anomalyRepository;

    public AnomalyController(AnomalyRepository anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
    }

    @GetMapping()
    public List<Anomaly> getAll() {
        return anomalyRepository.findAll();
    }



    @GetMapping("/with-observations")
    public List<Anomaly> getAllWithObservations() {
        return anomalyRepository.findAllWithObservations();
    }


}
