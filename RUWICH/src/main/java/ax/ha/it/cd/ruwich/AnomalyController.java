package ax.ha.it.cd.ruwich;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Anomaly addAnomaly(@RequestBody Anomaly anomaly) {
        return anomalyRepository.save(anomaly);
    }


    @GetMapping("/with-observations")
    public List<Anomaly> getAllWithObservations() {
        return anomalyRepository.findAllWithObservations();
    }


}
