package example.utils;

import io.gatling.javaapi.core.Session;
import java.util.List;
import java.util.Random;

public class Helper {


    public static Session videoGameId(Session session, String list, String gameId) {
        List<Integer> allGamesVideoId = session.getList(list);
        if (!allGamesVideoId.isEmpty() && allGamesVideoId != null) {
            Random random = new Random();
            Integer randomId = allGamesVideoId.get(random.nextInt(allGamesVideoId.size()));
            return session.set(gameId, randomId);
        }
        return session;
    }
}
