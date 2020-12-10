package connection;

import com.aTorreNegra.model.TileMap;
import com.aTorreNegra.util.AnimationSet;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnimationSetDao {

    public AnimationSet consultaAnimationSet(int idFk) {
        AnimationSet m = new AnimationSet();
        String sql = "select * from animationSet where actorFk = ?";
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idFk);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                AssetManager assetManager = new AssetManager();
                assetManager.load("div/packed/tile/textures.atlas", TextureAtlas.class);
                assetManager.finishLoading();
                TextureAtlas atlas = assetManager.get("div/packed/tile/textures.atlas", TextureAtlas.class);
                m = new AnimationSet(
                        new Animation(0.45f / 2f, atlas.findRegions(rs.getString("enderecoA1")), Animation.PlayMode.LOOP_PINGPONG),
                        new Animation(0.45f / 2f, atlas.findRegions(rs.getString("enderecoA2")), Animation.PlayMode.LOOP_PINGPONG),
                        new Animation(0.45f / 2f, atlas.findRegions(rs.getString("enderecoA3")), Animation.PlayMode.LOOP_PINGPONG),
                        new Animation(0.45f / 2f, atlas.findRegions(rs.getString("enderecoA4")), Animation.PlayMode.LOOP_PINGPONG),
                        new Animation(0.20f / 2f, atlas.findRegions(rs.getString("enderecoA5")), Animation.PlayMode.LOOP),
                        new Animation(0.20f / 2f, atlas.findRegions(rs.getString("enderecoA6")), Animation.PlayMode.LOOP),
                        new Animation(0.20f / 2f, atlas.findRegions(rs.getString("enderecoA7")), Animation.PlayMode.LOOP),
                        new Animation(0.20f / 2f, atlas.findRegions(rs.getString("enderecoA8")), Animation.PlayMode.LOOP),
                        new Animation(0.20f / 2f, atlas.findRegions(rs.getString("enderecoA9")), Animation.PlayMode.LOOP),
                        new Animation(0.20f / 2f, atlas.findRegions(rs.getString("enderecoA10")), Animation.PlayMode.LOOP),
                        new Animation(0.20f / 2f, atlas.findRegions(rs.getString("enderecoA11")), Animation.PlayMode.LOOP),
                        new Animation(0.20f / 2f, atlas.findRegions(rs.getString("enderecoA12")), Animation.PlayMode.LOOP),
                        atlas.findRegion(rs.getString("enderecoA13")),
                        atlas.findRegion(rs.getString("enderecoA14")),
                        atlas.findRegion(rs.getString("enderecoA15")),
                        atlas.findRegion(rs.getString("enderecoA16"))
                );
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
