
package utils.db;

import lombok.extern.slf4j.Slf4j;
import org.apache.pinot.client.Connection;
import org.apache.pinot.client.ConnectionFactory;



@Slf4j
public class PinotDB {
    private PinotDB() {
    }
    private static PinotDB instance = null;

    public static PinotDB getInstance() {
        if (instance == null) {
            synchronized (PinotDB.class) {
                instance = new PinotDB();
            }
        }
        return instance;
    }


    public Connection getPinotConnection(String broker) {
        return ConnectionFactory.fromHostList(broker);
    }


}


