
package connection;

import com.aTorreNegra.model.TileMap;
import com.aTorreNegra.model.World;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileMapDao {
    public TileMap consultaTileMap(int idW){
        TileMap m = new TileMap();
        String sql = "select * from tilemap where worldFK = ?";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idW);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int id = rs.getInt("id");
                m = new TileMap(width, height, id);
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return m;
    }
    public TileMap consultaTileaMap(int idW){
        TileMap m = new TileMap();
        String sql = "select * from tilemap where id = ?";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idW);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int id = rs.getInt("id");
                m = new TileMap(width, height, id);
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return m;
    }
}
