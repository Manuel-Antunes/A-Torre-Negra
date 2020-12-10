package connection;

import com.aTorreNegra.model.Actor;
import com.aTorreNegra.model.Armor;
import com.aTorreNegra.model.Item;
import com.aTorreNegra.model.Tile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArmorDao {

    public ArrayList<Item> consultaAtu(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            String sql = "SELECT * FROM armor where itemFk = ?";
            try {

                Connection conn = Conexao.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, items.get(i).getId());
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    int ip = rs.getInt("ip");
                    int type = rs.getInt("type");
                    boolean equipado = rs.getBoolean("equipado");
                    if (type == 1 || type == 2 || type == 3) {
                        items.set(i, new Armor(items.get(i).getPeso(), items.get(i).getValor(), ip, type, items.get(i).getName(), items.get(i).getId(), equipado, items.get(i).getEndereco()));

                    }
                }
                pst.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return items;
    }

    public void atualizar(Armor a) {
        String sql = "INSERT INTO armor VALUES (?,?,?,?)";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, a.getId());
            pst.setInt(2, a.getTipo());
            pst.setFloat(3, a.getIp());
            pst.setBoolean(4, a.isActive());
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
