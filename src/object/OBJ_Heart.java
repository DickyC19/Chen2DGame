package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("icons/heart.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
