package example.simulations;

import static io.gatling.javaapi.core.CoreDsl.*;

import example.requests.APIEndpoints;
import example.scenarios.UserScenarios;
import io.gatling.javaapi.core.*;

public class GetSingleVideoGame extends Simulation {


    {
        setUp(UserScenarios.getSingleVideoGameScenario.injectOpen(atOnceUsers(3))).protocols(APIEndpoints.httpProtocol);
    }

}
