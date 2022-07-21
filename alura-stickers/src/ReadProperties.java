import java.io.IOException;
import java.util.Properties;

public final class ReadProperties {
  public static Properties getProp() throws IOException {
    Properties props = new Properties();
    props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
    return props;
  }

  public static String getProperty(String key) throws IOException {
    Properties props = new Properties();
    props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
    return props.getProperty(key);
  }
}
