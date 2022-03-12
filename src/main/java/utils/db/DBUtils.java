package utils.db;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DBUtils {
    Connection connection = null;
    private static DatabaseMetaData meta = null;

    @SneakyThrows
    public DBUtils(String server, String user, String password, String database) {
        connection = PostgresDB.getInstance().getConnection(server, user, password, database);
    }


    public String getUpdatedQuery(String query, String... params) {
        for (int i = 0; i < params.length; i++) {
            query = query.replace("PARAM" + (i + 1), params[i]);
        }
        log.info("Final Query: " + query);
        return query;
    }


    public int getIntColumnValue(String query, String columnName) {
        ResultSet rs;
        try (Statement statement = connection.createStatement()) {
            rs = statement.executeQuery(query);
            while (rs.next()) {
                return rs.getInt(columnName);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return 0;
    }


    public String getStringColumnValue(String query, String columnName) {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                return rs.getString(columnName);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public List<Integer> getIntListColumnValues(String query, String columnName) {
        List<Integer> values = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                values.add(rs.getInt(columnName));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return values;
    }


    public List<String> getStringListColumnValues(String query, String columnName) {
        List<String> values = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                values.add(rs.getString(columnName));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return values;
    }


    @SneakyThrows
    public synchronized boolean tableExists(String tableName) {
        if (meta == null) {
            log.info("Creating meta instance");
            meta = connection.getMetaData();
        }
        ResultSet resultSet = meta.getTables(null, null, tableName, new String[]{"TABLE"});
        return resultSet.next();
    }

    public boolean getBooleanColumnValue(String query, String columnName) {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                return rs.getBoolean(columnName);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return false;
    }
}
