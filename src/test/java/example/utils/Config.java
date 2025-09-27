package example.utils;

public class Config {


    public static String getBaseUrl() {
        return System.getProperty("baseUrl", "https://videogamedb.uk:443/api");
    }

    public static Integer getUsers() {
        return  Integer.parseInt(System.getProperty("users", "10"));
    }

    public static Integer getDuration() {
        return Integer.parseInt(System.getProperty("duration", "30"));
    }

    public static Integer getAtOnceUsers() {
        return Integer.parseInt(System.getProperty("users", "50"));
    }
}
