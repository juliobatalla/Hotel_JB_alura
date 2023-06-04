package ConnectionFactory;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TextConnection {
    public static void main(String[] args) {
        MySQLConnector connector = MySQLConnector.getInstance();
        try (Connection connection = connector.conectarBase()) {
            System.out.println("Conexión exitosa");
            realizarConsulta(connection);
        } catch (SQLException e) {
            System.out.println("Error de conexión");
            e.printStackTrace();
        } finally {
            connector.close();
        }
    }

    public static void realizarConsulta(Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM huespedes");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Conexión cerrada");
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión");
                e.printStackTrace();
            }
        }
    }

}
