package code.model;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;
import org.apache.log4j.Logger;
import org.apache.openejb.bonecp.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

import oracle.jdbc.*;
/**
 *
 */
public class ConnectionPool {

    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private BoneCP boneCp;

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    private ConnectionPool() {

        Properties dbProperties = new Properties();
        try (InputStream is = ConnectionPool.class.getClassLoader()
                .getResourceAsStream("database.properties")) {
            dbProperties.load(is);
            Class.forName("oracle.jdbc.OracleDriver");

            Locale.setDefault(Locale.ENGLISH);
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(dbProperties.getProperty("url"));
            config.setUsername(dbProperties.getProperty("user"));
            config.setPassword(dbProperties.getProperty("password"));
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(1);
            boneCp = new BoneCP(config);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            LOGGER.error(e);
        }
    }

    public Connection getConnection() throws SQLException {
        return boneCp.getConnection();
    }
}

