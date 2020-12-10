package connection;

import com.aTorreNegra.dialogue.Dialogue;
import com.aTorreNegra.dialogue.DialogueNode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DialogueNodeDao {

    public Dialogue addascoisa(Dialogue dialogue) {
        String sql = "select * from dialogueNode where dialogueFk = ?;";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, dialogue.getId());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int indice = rs.getInt("indice");
                String text = rs.getString("text");
                int type = rs.getInt("type");
                int pointer = rs.getInt("pointer");
                DialogueNode node = new DialogueNode(text, indice,id);
                if (type == 2) {
                    if (pointer != 0) {
                        node.makeLinear(pointer);
                    }
                } else if (type == 3) {
                    System.out.println("ta aqui");
                    ChoiceDao dao = new ChoiceDao();
                    node = dao.consultaTileMap(node);
                }
                dialogue.addNode(node);
            }
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return dialogue;
    }
}
