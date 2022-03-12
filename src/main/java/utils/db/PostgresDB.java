package utils.db;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class PostgresDB {

    private PostgresDB() {
    }

    private static PostgresDB instance = null;

    public static PostgresDB getInstance() {
        if (instance == null) {
            synchronized (PostgresDB.class) {
                instance = new PostgresDB();
            }
        }
        return instance;
    }


        public Connection getConnection(String server, String user, String password, String database) {
        Connection conn = null;
        String url = "jdbc:postgresql://" + server + "/" + database;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            log.error("PostgresDB connection failure: " + e.getMessage());
        }
        if (conn != null)
            log.info("Connected to the PostgreSQL server successfully.");
        return conn;
    }
}
