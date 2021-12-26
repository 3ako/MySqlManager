import mySqlManager.MySqlManager;
import mySqlManager.server.database.MySqlDatabase;
import mySqlManager.server.MySqlServer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main (String[] args) throws SQLException {
        MySqlManager manager = new MySqlManager();
        MySqlServer mySqlServer = manager.createServer("...", "", "");
        mySqlServer.startUi();
        mySqlServer.connect();
        mySqlServer.registerAllDatabases();
        MySqlDatabase base = mySqlServer.getBaseByName("authme");
        ResultSet rs = base.getOne("SELECT * FROM authme WHERE id > 0;");
    }
}
