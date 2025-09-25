package example.requests;

import example.utils.ReadingProperties;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import java.io.IOException;
import java.util.stream.Collectors;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


public class APIEndpoints extends Simulation {

    public static final HttpProtocolBuilder httpProtocol;

    static {
        try {
            httpProtocol = http.baseUrl(ReadingProperties.getFileValue("baseUrl"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static  HttpRequestActionBuilder getAllVideoGames() {
        return http("Get All Video Games")
                .get("/videogame")
                .check(status().is(200))
                .check(jsonPath("$[*].id").findAll().transform(id -> id.stream().map(ids -> Integer.parseInt(ids.toString())).collect(Collectors.toList())).saveAs("allGamesId"))
                .check(jsonPath("$[*].name").findAll().saveAs("gameTitle"));
    }

    public static HttpRequestActionBuilder getSingleVideoGame() {
        return http("Get Single Video Game")
                .get("/videogame/#{gameId}")
                .check(bodyString().saveAs("fullBody"))
                .check(status().is(200));
    }

    public static HttpRequestActionBuilder createVideoGame() {
        return http("Create Single Video Game")
                .post("/videogame")
                .headers(TokenAPI.getAuthorizationHeaders())
                .asJson()
                .body(ElFileBody("D:\\GATLING\\gatling-maven-plugin-demo-java-main\\src\\test\\java\\example\\utils\\VideoGameTemplate.json"))
                .check(status().is(200));

    }

    public static HttpRequestActionBuilder deleteVideoGame() {
        return http("Delete Video Game")
                .delete("/videogame/#{gameId}")
                .headers(TokenAPI.getAuthorizationHeaders())
                .check(status().is(200))
                .check(bodyString().is("Video game deleted"))
                .check(bodyString().saveAs("body"));
    }
}
