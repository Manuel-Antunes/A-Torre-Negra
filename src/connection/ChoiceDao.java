package connection;

import com.aTorreNegra.dialogue.DialogueNode;
import com.aTorreNegra.model.TileMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChoiceDao {
    public DialogueNode consultaTileMap(DialogueNode node){
        String sql = "select * from choice where dialoguenodeFk = ?";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, node.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                String text = rs.getString("text");
                int nextId = rs.getInt("nextIndex");
                System.out.println(text);
                node.addChoice(text, nextId);
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return node;
    }
}
