package connection;
import java.sql.*;
public class Conexao {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost/jogo";
        final String usuario = "root";
        final String senha = "root";
        Class.forName(driver);
        
        return DriverManager.getConnection(url, usuario, senha);
    }
}
