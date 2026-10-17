package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String PLATFORM = System.getProperty("platform", "android");

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties не найден в resources!");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("не смог загрузить config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static String getPlatform() {
        return PLATFORM.toLowerCase();
    }
}