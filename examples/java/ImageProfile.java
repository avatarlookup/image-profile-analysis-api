import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImageProfile {
    public static void main(String[] args) throws Exception {
        String apiKey = System.getenv("AVATARLOOKUP_API_KEY");
        if (apiKey == null) throw new IllegalStateException("Set AVATARLOOKUP_API_KEY");
        String body = "{\"service_type\":\"image_profile\",\"image_url\":\"https://example.com/portrait.jpg\"}";
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://avatarlookup.com/api/v1/image/profile"))
            .header("X-API-Key", apiKey)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(body))
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >= 300) throw new IllegalStateException(response.body());
        System.out.println(response.body());
    }
}
