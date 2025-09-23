package example.feeders;

import io.gatling.javaapi.core.FeederBuilder;

import static io.gatling.javaapi.core.CoreDsl.jsonFile;

public class MyFeeders {


    public static FeederBuilder<Object> feederBuilderSingleObject() {
        return  jsonFile("D:\\GATLING\\gatling-maven-plugin-demo-java-main\\src\\test\\java\\example\\utils\\singleVideoGame.json").queue();
    }

    public static FeederBuilder<Object> feederBuilderManyObjects() {
        return jsonFile("utils/VideoGames.json").circular();
    }

}
