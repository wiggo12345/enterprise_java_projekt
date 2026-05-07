package ax.ha.it.cd.ruwich;
import com.fasterxml.jackson.annotation.JsonView;

public class views {

    public static class WithoutObservation {}

    public static class WithObservation extends WithoutObservation {}
}