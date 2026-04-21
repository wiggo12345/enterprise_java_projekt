package ax.ha.it.cd.ruwich;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    private final ObservationController observationController;

    public ObservationController(ObservationController observationController) {
        this.observationController = observationController;
    }

    @GetMapping()
    public List<Anomaly> getAll() {
        return observationController.findAll();
    }
}
