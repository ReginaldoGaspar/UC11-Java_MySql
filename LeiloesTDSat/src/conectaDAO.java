
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Adm
 */
public class conectaDAO {
    
    public static String url = "jdbc:mysql://localhost:3306/uc11?" + "autoReconnect=true&useSSL=false"; /*+ "autoReconnect=true&useSSL=false"  - Usei para evitar mensagem Establishing
                                                                                        SSL connection without server's identity verification is not recommended” warning when connecting */
    public static String user = "root"; //nome do usuário do MySQL
    public static String password = "elge4ds5"; //senha do MySQL 

    public Connection connectDB() {
        Connection conn = null;

        try {

            conn = DriverManager.getConnection(url, user, password);
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }

}
