package example.simulations;

import static io.gatling.javaapi.core.CoreDsl.*;


import example.requests.APIEndpoints;
import example.scenarios.UserScenarios;
import io.gatling.javaapi.core.*;

public class GetAllVideoGames extends Simulation {


    {
        setUp(UserScenarios.getAllVideoGamesScenario.injectOpen(atOnceUsers(1))).protocols(APIEndpoints.httpProtocol);
    }

}
