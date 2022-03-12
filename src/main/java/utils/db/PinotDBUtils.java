package utils.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.pinot.client.Connection;
import org.apache.pinot.client.Request;
import org.apache.pinot.client.ResultSet;
import org.apache.pinot.client.ResultSetGroup;


@Slf4j
public class PinotDBUtils {
    Connection connection = null;


    public PinotDBUtils(String broker) {
        connection = PinotDB.getInstance().getPinotConnection(broker);
    }


    public String getUpdatedQuery(String query, String... params) {
        for (int i = 0; i < params.length; i++) {
            query = query.replace("PARAM" + (i + 1), params[i]);
        }
        log.info("Final Query: " + query);
        return query;
    }



    public int getIntColumnValue(String query, String columnName) {
        Request pinotClientRequest = new Request("sql", query);
        ResultSetGroup pinotResultSetGroup = connection.execute(pinotClientRequest);
        ResultSet resultSet = pinotResultSetGroup.getResultSet(0);
        int columnIndex = 0;
        for (int i = 0; i < resultSet.getColumnCount(); i++) {
            if (resultSet.getColumnName(i).equals(columnName))
                columnIndex = i;
        }
        int value = resultSet.getInt(0, columnIndex);
        log.info("DB value: " + value);
        return value;
    }


    public String getStringColumnValue(String query, String columnName) {
        Request pinotClientRequest = new Request("sql", query);
        ResultSetGroup pinotResultSetGroup = connection.execute(pinotClientRequest);
        ResultSet resultSet = pinotResultSetGroup.getResultSet(0);
        int columnIndex = 0;
        for (int i = 0; i < resultSet.getColumnCount(); i++) {
            if (resultSet.getColumnName(i).equals(columnName))
                columnIndex = i;
        }
        String value = resultSet.getString(0, columnIndex);
        log.info("DB value: " + value);
        return value;
    }


}
