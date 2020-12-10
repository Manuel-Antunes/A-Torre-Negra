package connection;

import com.aTorreNegra.model.Armor;
import com.aTorreNegra.model.Inventory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDao {
    
    public Inventory consultarG(int idA) {
        Inventory a = new Inventory();
        String sql = "select * from inventory where actorFk = ?;";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idA);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                a = new Inventory(id, idA);
            }
            System.out.println("foi");
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return a;
    }
    
    public void atualizar(Inventory a) {
        String sql = "DELETE FROM inventory WHERE id=?";
        String sql1 = "INSERT INTO inventory VALUES (?,?) ";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, a.getId());
            pst.execute();
            pst.close();
            
            PreparedStatement pst1 = conn.prepareStatement(sql1);
            pst1.setInt(1, a.getId());
            pst1.setInt(2, a.getIdFk());
            pst1.execute();
            
            pst1.close();
            conn.close();
            for (int i = 0; i < a.getItems().size(); i++) {
                ItemDao daoI = new ItemDao();
                daoI.atualizar(a.getItems().get(i), a.getId());
                if (a.getItems().get(i).getClass().getName() == "com.aTorreNegra.model.Armor") {
                    ArmorDao daoA = new ArmorDao();
                    daoA.atualizar((Armor) a.getItems().get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
