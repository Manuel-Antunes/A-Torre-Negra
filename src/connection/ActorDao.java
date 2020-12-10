package connection;

import com.aTorreNegra.model.Actor;
import com.aTorreNegra.model.Tile;
import com.aTorreNegra.model.TileMap;
import com.aTorreNegra.model.World;
import java.sql.*;

public class ActorDao {

    public Actor consultaTileMap(Tile tile) {
        Actor m = new Actor();
        String sql = "select * from actor where tileXFK = ? and tileYFK = ?";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, tile.getX());
            pst.setInt(2, tile.getY());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int xF = rs.getInt("tileXFK");
                int yF = rs.getInt("tileYFK");
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float sizeX = rs.getFloat("sizeX");
                float sizeY = rs.getFloat("sizeY");
                int dano = rs.getInt("dano");
                int hp = rs.getInt("dano");
                boolean active = rs.getBoolean("ativo");
                boolean alive = rs.getBoolean("alive");
                m = new Actor(tile.getX() - 1, tile.getY() - 1, name, sizeX, sizeY, hp, dano, id, tile.getMymap(), active,alive);
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        if (m.getName() != null && m.isAlive()) {
            return m;
        } else {
            return null;
        }

    }

    public void atualizar(Actor a) {
        String sql = "UPDATE actor SET tileXFk=?, tileYFk=?, alive=? WHERE id=?";

        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, a.getX()+1);
            pst.setInt(2, a.gety()+1);
            pst.setBoolean(3, a.isAlive());
            System.out.println(a.isAlive());
            pst.setInt(4, a.getId());
            pst.executeUpdate();

            pst.close();
            conn.close();
            InventoryDao dao = new InventoryDao();
            dao.atualizar(a.getInventory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
