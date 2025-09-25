package example.scenarios;

import example.feeders.MyFeeders;
import example.requests.APIEndpoints;
import example.requests.TokenAPI;
import example.utils.Helper;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;


public class UserScenarios {

    public static ScenarioBuilder getAllVideoGamesScenario = scenario("Get All Video Games Scenario")
            .exec(APIEndpoints.getAllVideoGames());

    public static ScenarioBuilder getSingleVideoGameScenario = scenario("Get Single Video Game Scenario")
            .exec(APIEndpoints.getAllVideoGames())
            .pace(2)
            .exec(session -> {
                return Helper.videoGameId(session,"allGamesId", "gameId");
            })
            .exec(APIEndpoints.getSingleVideoGame())
            .exec(session -> {
                System.out.println("GAME ID: " + session.getString("gameId"));
                System.out.println("GAME: " + session.getString("fullBody"));
                return session;
            });


    public static ScenarioBuilder createSingleVideoGameScenario = scenario("Load Video Game And Create")
            .exec(feed(MyFeeders.feederBuilderManyObjects()))
            .pace(2)
            .exec(TokenAPI.getToken())
            .pace(2)
            .exec(APIEndpoints.createVideoGame());

    public static ScenarioBuilder deleteSingleVideoGameScenario = scenario("Delete Random Video Game")
            .exec(APIEndpoints.getAllVideoGames())
            .pace(2)
            .exec(session -> {
                return Helper.videoGameId(session,"allGamesId", "gameId");

            })
            .exec(TokenAPI.getToken())
            .exec(APIEndpoints.deleteVideoGame())
            .exec(session -> {
                System.out.println("BODY: " + session.getString("body"));
                return session;
            });

    public static ScenarioBuilder CRUDScenario = scenario("CRUD")
            .exec(feed(MyFeeders.feederBuilderManyObjects()))
            .pace(2)
            .exec(TokenAPI.getToken())
            .pace(2)
            .exec(APIEndpoints.createVideoGame())
            .exec( session -> {
                System.out.println("BODY: " + session.getString("fullBody"));
                return session;
            })
            .pace(2)
            .exec(APIEndpoints.getSingleVideoGame())
            .exec( session -> {
                System.out.println("GAME ID: " + session.getString("gameId"));
//                System.out.println("GAME : " + session.getString("fullBody"));
                return session;
            })
            .pace(2)
            .exec(APIEndpoints.deleteVideoGame());
}
