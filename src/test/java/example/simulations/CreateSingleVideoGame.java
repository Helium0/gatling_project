package example.simulations;

import example.requests.APIEndpoints;
import example.scenarios.UserScenarios;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;

public class CreateSingleVideoGame extends Simulation {


    {
        setUp(UserScenarios.createSingleVideoGameScenario.
                injectOpen(atOnceUsers(1)))
                .protocols(APIEndpoints.httpProtocol);
    }

}
