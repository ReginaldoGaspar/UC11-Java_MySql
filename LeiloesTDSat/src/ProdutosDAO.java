
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

    public String cadastrarProduto(ProdutosDTO produto) {
        try {
            conectar();
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setInt(1, 0);// para o sgbd controlar o id
            st.setString(2, produto.getNome());
            st.setDouble(3, produto.getValor());
            st.setString(4, produto.getStatus());
            st.executeUpdate();
            return "Salvo com sucesso";
        } catch (SQLException ex) {
            return "Erro ao salvar";
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

    public String venderProduto(int id) {
        try {
            conectar();

            st = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE produtos.id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return "Atualizado com sucesso";
        } catch (SQLException e) {
            return "Erro ao atualizar";
        }
    }
    
    public String cancelarVenda(int id) {
        try {
            conectar();

            st = conn.prepareStatement("UPDATE produtos SET status = 'A Venda' WHERE produtos.id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return "Atualizado com sucesso";
        } catch (SQLException e) {
            return "Erro ao atualizar";
        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
     try {
            conectar();

            st = conn.prepareStatement("SELECT * FROM produtos WHERE produtos.status = 'Vendido'"); // somente os Vendidos
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
