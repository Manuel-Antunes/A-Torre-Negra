package connection;

import com.aTorreNegra.model.Item;
import com.aTorreNegra.model.Tile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDao {

    public ArrayList<Item> listarItems(int idI) {

        String sql = "SELECT * FROM item where inventoryFk = ?";
        ArrayList<Item> lista = new ArrayList<>();

        try {

            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idI);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                float value = rs.getInt("value");
                String name = rs.getString("name");
                float peso = rs.getFloat("peso");
                String endereco = rs.getString("endereco");
                lista.add(new Item(name, peso, value, id, endereco));
            }
            pst.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return lista;
    }

    public void atualizar(Item item, int idfk) {
        String sql = "INSERT INTO item VALUES (?,?,?,?,?,?)";

        try {

            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, item.getId());
            pst.setString(2, item.getName());
            pst.setFloat(3, item.getValor());
            pst.setInt(4, idfk);
            pst.setFloat(5, item.getPeso());
            pst.setString(6, item.getEndereco());
            pst.execute();

            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
