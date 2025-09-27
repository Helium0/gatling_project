package example.feeders;

import io.gatling.javaapi.core.FeederBuilder;

import static io.gatling.javaapi.core.CoreDsl.jsonFile;

public class MyFeeders {


    public static FeederBuilder<Object> feederBuilderForCreateVideoGame() {
        return jsonFile("bodies/VideoGames.json").random();
    }

    public static FeederBuilder<Object> feederBuilderForUpdateVideoGame() {
        return jsonFile("bodies/VideoGamesUpdate.json").random();
    }

}
