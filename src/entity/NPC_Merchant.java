package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class NPC_Merchant extends Entity{
    BufferedImage image;
    boolean isDrawn;

    public NPC_Merchant(GamePanel gp) {
        super(gp);
        isDrawn = false;
        solidArea = new Rectangle(0, 0, 64, 64);
        x = gp.tileSize * 8;
        y = gp.screenHeight - gp.tileSize * 2;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;

        setDialogue();
    }

    public void drawNPC(Graphics2D g2) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("npc/merchant1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        isDrawn = true;
    }

    public void setDrawn(boolean drawn) {
        isDrawn = drawn;
    }

    public void setDialogue() {
        dialogues[0] = "Hello traveler, You can level up and buy items\nhere";
    }

    public void speak() {
        super.speak();
        gp.gameState = gp.tradeState;
    }


}
