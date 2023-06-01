package main;

import java.awt.*;

public class UI {

    GamePanel gp;
    Graphics2D g2;

    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gp.gameState == gp.playState) {

        }
        if (gp.gameState == gp.pauseState) {

        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
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


}
