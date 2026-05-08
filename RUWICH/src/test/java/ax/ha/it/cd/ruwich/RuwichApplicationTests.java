package ax.ha.it.cd.ruwich;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AnomalyIntegrationTest {

static {
    Dotenv dotenv = Dotenv.configure()
            .directory("../")
            .load();

    java.lang.System.setProperty("DB_URL", dotenv.get("DB_URL"));
    java.lang.System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
    java.lang.System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
}


    private static int id;

    @LocalServerPort
    private int port;

@Order(1)
@Test
void testCreateAnomaly() throws Exception {

    URL url = new URL("http://localhost:" + port + "/api/anomalies");

    HttpURLConnection connection =
            (HttpURLConnection) url.openConnection();

    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setDoOutput(true);

    String json = """
        {
            "name": "Integration Test",
            "description": "Created by integration test",
            "classification": "Test"
        }
        """;

    try(OutputStream os = connection.getOutputStream()) {
        byte[] input = json.getBytes();
        os.write(input, 0, input.length);
    }

    int responseCode = connection.getResponseCode();

    System.out.println("POST response code: " + responseCode);

    assertEquals(200, responseCode);
}
    @Order(2)
    @Test
    void testGetAnomalyByClassification() throws Exception {

        URL url = new URL("http://localhost:" + port + "/api/anomalies/classification/Test");

        HttpURLConnection getConnection =
                (HttpURLConnection) url.openConnection();

        getConnection.setRequestMethod("GET");

        int getResponseCode = getConnection.getResponseCode();

        System.out.println("GET response code: " + getResponseCode);

        assertEquals(200, getResponseCode);

        // ---------------- READ RESPONSE ----------------

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(getConnection.getInputStream())
        );

        StringBuilder response = new StringBuilder();

        String line;

        while((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        String responseBody = response.toString();

        System.out.println(responseBody);

        //Plocka ut id
        String idString = responseBody.split("\"id\":")[1].split(",")[0];
        id = Integer.parseInt(idString);

        assertTrue(id > 0);

        System.out.println("Found ID: " + id);


        // ---------------- ASSERT ----------------

        assertTrue(responseBody.contains("Integration Test"));
    }


    @Order(3)
    @Test
    void testDeleteAnomalyById() throws Exception {
        System.out.println("ID to delete: " + id);


        URL deleteUrl = new URL(
                "http://localhost:" + port +
                        "/api/anomalies/" + id
        );

        HttpURLConnection deleteConnection =
                (HttpURLConnection) deleteUrl.openConnection();

        deleteConnection.setRequestMethod("DELETE");

        int deleteResponseCode = deleteConnection.getResponseCode();

        System.out.println("DELETE response code: " + deleteResponseCode);

        //Assert
        assertEquals(200, deleteResponseCode);

        //verify deletion

        URL getUrl = new URL(
                "http://localhost:" + port +
                        "/api/anomalies/classification/Test"
        );

        HttpURLConnection getConnection =
                (HttpURLConnection) getUrl.openConnection();

        getConnection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(getConnection.getInputStream())
        );

        StringBuilder response = new StringBuilder();

        String line;

        while((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();

        String responseBody = response.toString();

        System.out.println(responseBody);

        assertFalse(responseBody.contains("Integration Test"));

}


    }



