package example.simulations;

import example.requests.APIEndpoints;
import example.scenarios.UserScenarios;
import example.utils.Config;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;

public class CreateSingleVideoGame extends Simulation {


    {
        setUp(UserScenarios.createSingleVideoGameScenario.
                injectOpen(rampUsers(Config.getUsers()).during(Config.getDuration()),
                        constantUsersPerSec(5).during(Config.getDuration()),
                        rampUsersPerSec(1).to(10).during(Config.getDuration()),
                        constantUsersPerSec(2).during(Config.getDuration()),
                        atOnceUsers(Config.getAtOnceUsers()))
                .protocols(APIEndpoints.httpProtocol));
    }
}
