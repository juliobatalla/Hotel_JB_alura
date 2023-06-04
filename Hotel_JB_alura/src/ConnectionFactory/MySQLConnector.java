package ConnectionFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnector {
    private static MySQLConnector instance;
    private final ComboPooledDataSource dataSource;

    public MySQLConnector() {
        dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/jb_hotel");
        dataSource.setUser("admin");
        dataSource.setPassword("bata1234");
    }

    public static synchronized MySQLConnector getInstance() {
        if (instance == null) {
            instance = new MySQLConnector();
        }
        return instance;
    }

    public Connection conectarBase() throws SQLException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión");
            e.printStackTrace();
        }
        return connection;
    }
    
    public void close() {
        dataSource.close();
    }

    public static Connection getConnection() {
        try {
            return getInstance().conectarBase();
        } catch (SQLException e) {
            System.out.println("Error al obtener la conexión");
            e.printStackTrace();
            return null;
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public PreparedStatement prepareStatement(String sql) {
        try {
            Connection connection = conectarBase();
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

