package example.scenarios;

import example.feeders.MyFeeders;
import example.requests.APIEndpoints;
import example.requests.TokenAPI;
import example.utils.Helper;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.internal.HttpCheckBuilders.*;


public class UserScenarios {

    public static ChainBuilder getAllVideoGames =
            exec(APIEndpoints.getAllVideoGamesRequest());

    public static ScenarioBuilder getSingleVideoGameScenario = scenario("Get Single Video Game Scenario")
            .exec(getAllVideoGames)
            .pace(2)
            .exec(session -> {
                return Helper.videoGameId(session,"allGamesId", "gameId");
            })
            .exec(APIEndpoints.getSingleVideoGameRequest());


    public static ScenarioBuilder createSingleVideoGameScenario = scenario("Load Video Game And Create")
            .exec(feed(MyFeeders.feederBuilderForCreateVideoGame()))
            .pace(2)
            .exitHereIfFailed()
            .exec(TokenAPI.getToken())
            .tryMax(3)
            .on(exec(APIEndpoints.createVideoGameRequest()
                    .check(status().in(200,201),
                            responseTimeInMillis().lt(1000)))
            .exec(APIEndpoints.createVideoGameRequest()))
            .doIf(session ->
                    session.isFailed()).then(exec(session -> {
                System.out.println("Failed create game after 3 attempts");
                return session;
            }));

    public static ScenarioBuilder deleteSingleVideoGameScenario = scenario("Delete Random Video Game")
            .exec(getAllVideoGames)
            .pace(2)
            .exec(session -> {
                return Helper.videoGameId(session,"allGamesId", "gameId");

            })
            .exec(TokenAPI.getToken())
            .exec(APIEndpoints.deleteVideoGameRequest());

    public static ScenarioBuilder updateSingleVideoGameScenario = scenario("Update Random Video Game")
            .exec(feed(MyFeeders.feederBuilderForUpdateVideoGame()))
            .pace(2)
            .exitHereIfFailed()
            .exec(getAllVideoGames)
            .pace(2)
            .exec(session -> {
                return Helper.videoGameId(session,"allGamesId", "gameId");
            })
            .exec(TokenAPI.getToken())
            .pace(2)
            .exec(APIEndpoints.updateVideoGameRequest());
}
