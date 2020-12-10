package connection;

import com.aTorreNegra.dialogue.Dialogue;
import com.aTorreNegra.model.TileMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DialogueDao {

    public Dialogue consultaDialogue(int actor1, int actor2, ArrayList<Dialogue> dialogueUsed) {
        Dialogue m = null;

        String sql = "select dialogue.* from dialogue, actor a, actor b, dialogar where dialogue.id = dialogar.dialogueFk and b.id = dialogar.actor1Fk and a.id = dialogar.actor2Fk and a.id = ? and b.id = ? and dialogue.state = 1;";
        try {

            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, actor2);
            pst.setInt(2, actor1);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int indice = rs.getInt("indice");
                boolean state = rs.getBoolean("state");
                int foi = 1;
                for (int i = 0; i < dialogueUsed.size(); i++) {
                    if (dialogueUsed.get(i).getId() == id) {
                        foi = 0;
                        break;
                    } else {
                        foi = 1;
                    }
                }
                if (foi == 1) {
                    m = new Dialogue(id, indice, state);
                }
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        DialogueNodeDao dao = new DialogueNodeDao();
        m = dao.addascoisa(m);
        return m;
    }

}
