package example.requests;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.http.*;
import java.util.Map;

public class TokenAPI {

    public static HttpRequestActionBuilder getToken() {

        return http("Get Authorization Token")
                .post("/authenticate")
                .asJson()
                .body(RawFileBody("adminCredentials.json"))
                .check(jsonPath("$.token").saveAs("accessToken"))
                .check(bodyString().saveAs("fullBody"))
                .check(status().is(200));
    }

    public static Map<CharSequence, String> getAuthorizationHeaders() {
        return Map.of(
                "Content-Type", "application/json",
                "Authorization", "Bearer #{accessToken}"
        );
    }
}
