import com.google.gson.Gson;
import dao.Sql2oCommentDao;
import org.sql2o.Connection;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        Sql2oCommentDao commentDao = new Sql2oCommentDao(DB.sql2o);
        Connection conn = DB.sql2o.open();
        Gson gson = new Gson();




    }
}
