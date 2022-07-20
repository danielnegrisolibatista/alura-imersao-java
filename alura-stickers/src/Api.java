import java.io.IOException;
import java.util.Properties;

public final class Api {
  public static String getUrlAPI() throws IOException {
    Properties props = ReadProperties.getProp();

    String apiUrl = props.getProperty("api_url_imdb");
    String apiVersion = props.getProperty("api_version_imbd");
    String apiKey = props.getProperty("api_key_imdb");

    return String.format("%s/%s/%s", apiUrl, apiVersion , apiKey);
  }

  public static String getTop250Movies() throws IOException {
    Properties props = ReadProperties.getProp();

    String apiUrl = getUrlAPI();
    String apiResource = props.getProperty("resource_top_250_movies");

    return formatUrl(apiUrl, apiResource);
  }

  public static String getTop250TVs() throws IOException {
    Properties props = ReadProperties.getProp();

    String apiUrl = getUrlAPI();
    String apiResource = props.getProperty("resource_top_250_tvs");

    return formatUrl(apiUrl, apiResource);
  }

  public static String getMostPopularMovies() throws IOException {
    Properties props = ReadProperties.getProp();

    String apiUrl = getUrlAPI();
    String apiResource = props.getProperty("resource_most_popular_movies");

    return formatUrl(apiUrl, apiResource);
  }

  public static String getMostPopularTVs() throws IOException {
    Properties props = ReadProperties.getProp();

    String apiUrl = getUrlAPI();
    String apiResource = props.getProperty("resource_most_popular_tvs");

    return formatUrl(apiUrl, apiResource);
  }

  private static String formatUrl(String apiUrl, String apiResource){
    return String.format("%s/%s", apiUrl, apiResource);
  }
}
