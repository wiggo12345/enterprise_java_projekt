package ax.ha.it.cd.ruwich;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RuwichApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();

        java.lang.System.setProperty("DB_URL", dotenv.get("DB_URL"));
        java.lang.System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        java.lang.System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

        SpringApplication.run(RuwichApplication.class, args);
    }
}
