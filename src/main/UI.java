package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    BufferedImage image;
    GamePanel gp;
    Graphics2D g2;

    public int commandNum = 0;

    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {

        }
        if (gp.gameState == gp.pauseState) {

        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "(not) Elden Ring";
        int textX = getXCenteredText(text);
        int textY = gp.tileSize * 2;

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/title1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, gp.screenWidth / 4, gp.tileSize * 3 - 32, image.getWidth(), image.getHeight(), null);

        g2.setColor(new Color(139, 128, 0));
        g2.drawString(text, textX + 5, textY + 5);
        g2.setColor(Color.YELLOW);
        g2.drawString(text, textX, textY);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "NEW GAME";
        textX = getXCenteredText((text));
        textY += gp.tileSize * 9;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - gp.tileSize, textY);
        }

        text = "QUIT ";
        textX = getXCenteredText((text));
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - gp.tileSize, textY);
        }

    }
    public void drawDialogueScreen() {
        int dialogueX = gp.tileSize * 2;
        int dialogueY = gp.tileSize / 2;
        int dialogueWidth = gp.screenWidth - (gp.tileSize * 4);
        int dialogueHeight = gp.tileSize * 5;

        drawSubWindow(dialogueX, dialogueY, dialogueWidth, dialogueHeight, g2);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        dialogueX += gp.tileSize;
        dialogueY += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, dialogueX, dialogueY);
            dialogueY += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height, Graphics2D g2) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }


}
