import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
public class App {
    public static void main(String[] args) throws Exception {

      // fazer uma conexão HTTP e buscar os top 250 filmes
      String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";

      URI address = URI.create(url);
      var client = HttpClient.newHttpClient();
      var request = HttpRequest.newBuilder(address).GET().build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      String body = response.body();
       
      // extrair só os dados que interessam (titulo, poster, classificação)
      var parser = new JsonParser();
      List<Map<String, String>> moviesList = parser.parse(body);

      // exibir e manipular os dados 
      for (Map<String,String> movie : moviesList) {
        System.out.println(movie.get("title"));
        System.out.println(movie.get("image"));
        System.out.println(movie.get("imDbRating"));
        System.out.println();
      }
    } 
}
