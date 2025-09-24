package example.scenarios;

import example.requests.APIEndpoints;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;


public class UserScenarios {

    public static ScenarioBuilder getAllUsersScenario = scenario("Get All Users Scenario")
        .exec(APIEndpoints.getAllUsers());

    public static ScenarioBuilder getSingleUserScenario = scenario("Get Single User Scenario")
        .exec(APIEndpoints.getSingleUser());

    public static ScenarioBuilder createUser = scenario("Create User")
        .exec(APIEndpoints.createUser());

    public static ScenarioBuilder createUserFeeder = scenario("Multiple Users")
            .exec(feed(jsonFile("utils/user.json").circular()));

}
