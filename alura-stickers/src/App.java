import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
public class App {
    public static void main(String[] args) throws Exception {
      // fazer uma conexão HTTP e buscar os top 250 filmes
      List<Map<String, String>> moviesList = getMovies();

      // exibir e manipular os dados 
      for (Map<String,String> movie : moviesList) {
          
        printMovie(movie);

        generateSticker(movie);
      }
    }

    private static List<Map<String, String>> getMovies() throws IOException, InterruptedException {
      // fazer uma conexão HTTP e buscar os top 250 filmes
      String url = Api.getTop250Movies();
      //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";

      URI address = URI.create(url);
      var client = HttpClient.newHttpClient();
      var request = HttpRequest.newBuilder(address).GET().build();
      HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
      String body = response.body();
       
      // extrair só os dados que interessam (titulo, poster, classificação)
      var parser = new JsonParser();

      return parser.parse(body);
    }

    private static void printMovie(Map<String,String> movie) {
      System.out.println("");

      System.out.println("Título: " + movie.get("title"));
      System.out.println("Poster: " + movie.get("image"));
      System.out.println("\u001b[46mClassificação: " + movie.get("imDbRating")+"\u001b[m");

      Integer imDbRating = (Math.round(Float.parseFloat(movie.get("imDbRating"))));
      System.out.println("Avaliação: " + "*".repeat(imDbRating));
     
    }

    private static void generateSticker(Map<String,String> movie) throws Exception {
      String folderName = ReadProperties.getProperty("sticker_output_folder_name");

      String title = movie.get("title");
      String urlImag = movie.get("image");
      InputStream inputStream = new URL(urlImag).openStream();
      String fileName = title + ".png";

      GenerateSticker generateSticker = new GenerateSticker();
      generateSticker.create(inputStream, folderName, fileName, "TOP");
    }
}
