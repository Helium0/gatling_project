package example.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadingProperties {


    private static FileInputStream file() throws FileNotFoundException {
        return new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\resources\\websiteURL");
    }

    public static String getFileValue(String value) throws IOException {
        Properties properties = new Properties();
        properties.load(file());
        String fileValue = properties.getProperty(value);
        return fileValue;
    }
}
