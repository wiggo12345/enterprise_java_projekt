package ax.ha.it.cd.ruwich;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AnomalyIntegrationTest {

    private int id;

    @LocalServerPort
    private int port;
/*
    @Test
    void shouldReachEndpoint() throws Exception {
        URL url = new URL("http://localhost:" + port + "/api/anomalies");

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        System.out.println("Response code: " + responseCode);

        assertEquals(200, responseCode);
    }*/
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
        System.out.println("Found ID: " + id);


        // ---------------- ASSERT ----------------

        assertTrue(responseBody.contains("Integration Test"));
    }

    
    }



