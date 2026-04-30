package ax.ha.it.cd.ruwich;

import org.springframework.stereotype.Service;

@Service
public class AnomalyService {
    private AnomalyRepository anomalyRepository;

    public AnomalyService(AnomalyRepository anomalyRepository) {
        this.anomalyRepository = anomalyRepository;
    }

    //Service for checking that anomaly exists, by checking id
    public Anomaly createAnomaly(Anomaly anomaly) {

        try {
            Classification.valueOf(anomaly.getClassification().name());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid classification");
        }

        String description = anomaly.getDescription();

        if (description != null && description.length() > 400) {
            throw new IllegalArgumentException("Description must be less than 400 characters");
        }

        return anomalyRepository.save(anomaly);
    }



    public Anomaly updateAnomalyDescription(Integer id, String description) {

        if (description != null && description.length() > 400) {
            throw new IllegalArgumentException("Description must be less than 400 characters");
        }

        Anomaly anomaly = anomalyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anomaly not found"));
        anomaly.setDescription(description);
        return anomalyRepository.save(anomaly);
    }


}
