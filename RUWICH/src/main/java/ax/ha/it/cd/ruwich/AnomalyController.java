package ax.ha.it.cd.ruwich;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anomalies")


@Transactional
public class AnomalyController {
    private final AnomalyRepository anomalyRepository;
    private final ObservationRepository observationRepository;

    public AnomalyController(AnomalyRepository anomalyRepository, ObservationRepository observationRepository) {
        this.anomalyRepository = anomalyRepository;
        this.observationRepository = observationRepository;
    }

    @GetMapping()
    @JsonView(views.WithoutObservation.class)
    public List<Anomaly> getAll() {
        return anomalyRepository.findAll();
    }


    @GetMapping("/classification/{classification}")
    @JsonView(views.WithoutObservation.class)
    public List<Anomaly> getAllByClassification(@PathVariable Classification classification) {
        return anomalyRepository.findByClassification(classification);
    }


    @PostMapping
    @JsonView(views.WithoutObservation.class)
    public Anomaly addAnomaly(@Valid @RequestBody Anomaly anomaly) {
        return anomalyRepository.save(anomaly);
    }


    @GetMapping("/{id}")
    @JsonView(views.WithObservation.class)
    public Anomaly findByID(@PathVariable int id) {
        return anomalyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }


    @PutMapping("/updateDescription/{id}/description")
    @JsonView(views.WithoutObservation.class)
    public Anomaly updateDescription(@PathVariable Integer id, @RequestBody String description) {


        Anomaly anomaly = anomalyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anomaly not found"));
        anomaly.setDescription(description);
        return anomalyRepository.save(anomaly);


    }





    @Transactional
    @DeleteMapping("/{id}")
    public void deleteAnomaly(@PathVariable int id) {
        anomalyRepository.deleteById(id);
    }




}
