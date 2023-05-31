package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC_OldMan extends Entity{
    BufferedImage image;
    boolean isDrawn;

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        isDrawn = false;
        solidArea = new Rectangle(0, 0, 64, 64);
        x = gp.tileSize * 12;
        y = gp.tileSize * 6;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }

    public void drawNPC(Graphics2D g2) {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, gp.tileSize * 12, gp.tileSize * 6, gp.tileSize, gp.tileSize, null);
        isDrawn = true;
    }

}
