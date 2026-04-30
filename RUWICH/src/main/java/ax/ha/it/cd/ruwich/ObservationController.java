
package ax.ha.it.cd.ruwich;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;




@RestController
@RequestMapping("/api/observations")
public class ObservationController {
    private final ObservationRepository observationRepository;

    public ObservationController(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    @GetMapping()
    public Iterable<Observation> getAll() {
        return observationRepository.findAll();
    }

    @PostMapping
    public Observation addObservation(@RequestBody Observation observation) {
        return observationRepository.save(observation);
    }




    @PutMapping("/updateDescription/{id}/description")
    public Observation updateDescription(@PathVariable Integer id, @RequestBody String description) {


        Observation observation = observationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Observation not found"));
        observation.setDescription(description);
        return observationRepository.save(observation);


    }




    @Transactional
    @DeleteMapping("/deleteObservation/{id}")
    public void deleteObservation(@PathVariable int id) {
        observationRepository.deleteById(id);
    }

}