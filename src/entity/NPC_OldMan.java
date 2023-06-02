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

        setDialogue();
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
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        isDrawn = true;
    }
    public void setDialogue() {
        dialogues[0] = "Hello traveler, Welcome to (not) Elden Ring";
        dialogues[1] = "The top path will be the hardest \nbut will contain the most rewards. \n\nThe bottom path will be the easiest \nbut will contain very few. \n\nThe center path is right in the middle of the two ";
        dialogues[2] = "But wait! It's dangerous to go alone! Take this.";
        dialogues[3] = "*Obtained Sword*";
    }

    public void speak() {
        super.speak();
    }

}
