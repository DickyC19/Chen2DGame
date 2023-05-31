package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NPC_OldMan extends Entity{
    BufferedImage image;

    public NPC_OldMan(GamePanel gp) {
        super(gp);
        solidArea = new Rectangle(0, 0, 64, 64);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void drawNPC(Graphics2D g2) {
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("npc/oldman1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, gp.tileSize * 12, gp.tileSize * 6, gp.tileSize, gp.tileSize, null);
    }

}
