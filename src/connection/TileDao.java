package connection;

import com.aTorreNegra.model.Tile;
import com.aTorreNegra.model.TileMap;
import com.aTorreNegra.model.World;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TileDao {

    public ArrayList<Tile> listarClientes(TileMap map) {

        String sql = "SELECT * FROM tile where tilemapFk = ?";
        ArrayList<Tile> lista = new ArrayList<>();

        try {

            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, map.getId());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int x = rs.getInt("x");
                int y = rs.getInt("y");
                String endereco = rs.getString("endereco");
                lista.add(new Tile(x, y, map.getId(),map,endereco));
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
