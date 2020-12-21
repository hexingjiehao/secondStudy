package database.pgclient;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.PostgreSQLClient;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;

public class VertxPostgreSQLClient {

    public static void main(String[] args) {
        //1.创建client--共享
        Vertx vertx=Vertx.vertx();
        JsonObject postgreSQLClientConfig = new JsonObject().put("host", "mypostgresqldb.mycompany");
        SQLClient postgreSQLClient = PostgreSQLClient.createShared(vertx, postgreSQLClientConfig,"PostgreSQLPool1");

        //2.创建client--不共享
        JsonObject postgreSQLClientConfig2 = new JsonObject().put("host", "mypostgresqldb.mycompany");
        SQLClient postgreSQLClient2 = PostgreSQLClient.createNonShared(vertx, postgreSQLClientConfig2);

        //3.获取连接
        postgreSQLClient.getConnection(res -> {
            if (res.succeeded()) {
                SQLConnection connection = res.result();

                // Got a connection
            } else {
                // Failed to get connection - deal with it
            }
        });
    }

}
