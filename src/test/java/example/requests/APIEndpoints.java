package example.requests;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;


import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

public class APIEndpoints extends Simulation {

    public static final HttpProtocolBuilder httpProtocol = http.baseUrl("https://reqres.in/api")
            .header("x-api-key","reqres-free-v1");

    public static  HttpRequestActionBuilder getAllUsers() {
        return http("Get All Users")
                .get("/users")
                .check(status().is(200));
    }

    public static HttpRequestActionBuilder getSingleUser() {   //get hard coded user
        return http("Get Single User")
                .get("/users/2")
                .check(status().is(200));
    }

    public static HttpRequestActionBuilder createUser() {
        return http("Create User")
                .post("/users")
                .asJson()
                .body(RawFileBody("utils/user.json"))
                .check(status().is(201));
    }



}
