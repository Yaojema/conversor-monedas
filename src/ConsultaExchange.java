import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaExchange {
    Exchange buscarPar(String base, String target, int amount){
        URI direccion =URI.create("https://v6.exchangerate-api.com/v6/5b429369de552e2e69719e59/pair/"+base+"/"+target+"/"+amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Exchange.class);
        } catch (Exception e) {
            throw new RuntimeException("No logre encontrar esa Exchange");
        }

    }
}
