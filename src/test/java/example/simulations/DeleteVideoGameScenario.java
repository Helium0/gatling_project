package example.simulations;

import example.requests.APIEndpoints;
import example.scenarios.UserScenarios;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;

public class DeleteVideoGameScenario extends Simulation {


    {
        setUp(UserScenarios.deleteSingleVideoGameScenario.
                injectOpen(atOnceUsers(3)))
                .protocols(APIEndpoints.httpProtocol);
    }

}
