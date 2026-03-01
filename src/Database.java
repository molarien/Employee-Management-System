import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Database {

    public static String kullanici_adi;
    public static String parola;
    public static String db_ismi;
    public static String host;
    public static int port;

    static {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
            kullanici_adi = properties.getProperty("kullanici_adi");
            parola = properties.getProperty("parola");
            db_ismi = properties.getProperty("db_ismi");
            host = properties.getProperty("host");
            port = Integer.parseInt(properties.getProperty("port"));
        } catch (IOException e) {
            System.err.println("Ayar dosyası yüklenemedi!");
            e.printStackTrace();
        }
    }











}
