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
            throw new RuntimeException("Failed to load base URL", e);
        }
    }

    public static  HttpRequestActionBuilder getAllVideoGamesRequest() {
        return http("Get All Video Games")
                .get("/videogame")
                .check(status().is(200))
                .check(jsonPath("$[*].id").findAll().transform(id -> id.stream().map(ids -> Integer.parseInt(ids.toString())).collect(Collectors.toList())).saveAs("allGamesId"))
                .check(jsonPath("$[*].name").findAll().saveAs("gameTitle"));
    }

    public static HttpRequestActionBuilder getSingleVideoGameRequest() {
        return http("Get Single Video Game")
                .get("/videogame/#{gameId}")
                .check(bodyString().saveAs("fullBody"))
                .check(status().is(200));
    }

    public static HttpRequestActionBuilder createVideoGameRequest() {
        return http("Create Single Video Game")
                .post("/videogame")
                .headers(TokenAPI.getAuthorizationHeaders())
                .asJson()
                .body(ElFileBody("bodies/VideoGameTemplate.json"))
                .check(status().is(200));

    }

    public static HttpRequestActionBuilder deleteVideoGameRequest() {
        return http("Delete Video Game")
                .delete("/videogame/#{gameId}")
                .headers(TokenAPI.getAuthorizationHeaders())
                .check(status().is(200))
                .check(bodyString().is("Video game deleted"))
                .check(bodyString().saveAs("body"));
    }

    public static HttpRequestActionBuilder updateVideoGameRequest() {
        return http("Update Video Game")
                .put("/videogame/#{gameId}")
                .headers(TokenAPI.getAuthorizationHeaders())
                .body(ElFileBody("bodies/VideoGameTemplate.json"))
                .check(bodyString().saveAs("updateVideo"))
                .check(status().is(200));
    }
}
