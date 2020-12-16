import com.google.gson.Gson;
import dao.Sql2oCommentDao;
import models.Comment;
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


        post("/comments/new", "application/json", (req, res) -> {
            Comment comment = gson.fromJson(req.body(), Comment.class);
            commentDao.add(comment);
            res.status(400);
            res.type("application/json");
            return gson.toJson(comment);
        });

        get("/comments", "application/json", (req, res) -> { //accept a request in format JSON from an app
            res.type("application/json");
            return gson.toJson(commentDao.getAll());//send it back to be displayed
        });
        after((req, res) -> {
            res.type("application/json");
        });

    }


}
