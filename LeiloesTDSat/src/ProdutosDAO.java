
/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void conectar() {

        conn = new conectaDAO().connectDB();
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (Exception e) {
        }

    }

    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            conectar();
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setInt(1, 0);// para o sgbd controlar o id
            st.setString(2, produto.getNome());
            st.setDouble(3, produto.getValor());
            st.setString(4, produto.getStatus());
            st.executeUpdate();
        } catch (SQLException ex) {

        }
        
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            conectar();

            st = conn.prepareStatement("SELECT * FROM produtos");
            rs = st.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getDouble("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }

        } catch (Exception e) {
            System.out.println("erro " + e.getMessage());
        }
       
        return listagem;
        
    }

}
