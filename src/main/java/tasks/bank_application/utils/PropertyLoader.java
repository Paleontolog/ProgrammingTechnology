package tasks.bank_application.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    public static Properties loadProperties(String path) {
        FileInputStream fis;
        Properties property = new Properties();
        try {
            fis = new FileInputStream(path);
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
