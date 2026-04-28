package ax.ha.it.cd.ruwich;

import org.springframework.transaction.annotation.Transactional;
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


    @GetMapping("/{id}")
    public Anomaly findByID(@PathVariable int id) {
        return anomalyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }





    @Transactional
    @DeleteMapping("/{id}")
    public void deleteAnomaly(@PathVariable int id) {
        anomalyRepository.deleteById(id);
    }




}
