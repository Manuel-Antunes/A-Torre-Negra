package connection;

import com.aTorreNegra.model.World;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorldDao {
    public World consultarG() {
        World a = new World();
        String sql = "select * from world where active = true;";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("world.id");
                boolean active = rs.getBoolean("world.active");
                a = new World(id, active);
            }
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return a;
    }
    public ArrayList<World> listarWorlds() {

        String sql = "SELECT * FROM world";
        ArrayList<World> lista = new ArrayList<>();

        try {

            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("world.id");
                boolean active = rs.getBoolean("world.active");
                lista.add(new World(id, active));
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
}
