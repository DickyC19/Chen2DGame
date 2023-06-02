package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Enemy extends Entity {

    public String name;
    public int maxLife;
    public int life;
    public boolean isDrawn;

    public Enemy(GamePanel gp) {
        super(gp);
        isDrawn = false;
    }
    public void setImage(String filePath) {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemies/" + filePath + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Move determineMove() {
        double chance = Math.random();
        if (chance <= .33) {
            return moves[0];
        } else if (chance <= .66) {
            return moves[1];
        } else {
            return moves[2];
        }
    }

    public void resetHp() {
        life = maxLife;
    }

    public void drawEnemy(Graphics2D g2) {
        g2.drawImage(image, x, y, solidArea.width, solidArea.height, null);
        isDrawn = true;
    }

}
