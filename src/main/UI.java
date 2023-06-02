package main;

import entity.Enemy;
import entity.Entity;
import object.OBJ_Heart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {

    BufferedImage heart;
    BufferedImage arrow;
    BufferedImage fightSelect;
    BufferedImage moveList;
    BufferedImage redArrow;
    BufferedImage textBox;
    BufferedImage enemyImage;
    BufferedImage background1;
    BufferedImage background2;
    BufferedImage background3;
    BufferedImage background4;
    BufferedImage background5;
    Enemy enemy;
    GamePanel gp;
    Graphics2D g2;

    public int commandNum = 0;
    public int battleNum = 0;

    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        Entity heartObj = new OBJ_Heart(gp);
        heart = heartObj.image;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState) {
            drawHealth();
        }
        if (gp.gameState == gp.pauseState) {
            drawBattleScene();
        }
        if (gp.gameState == gp.dialogueState) {
            drawHealth();
            drawDialogueScreen();
        }
    }

    public void drawTitleScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "(not) Elden Ring";
        int textX = getXCenteredText(text);
        int textY = gp.tileSize * 2;
        BufferedImage image = null;
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

    public void drawBattleScene() {
        if (!gp.monsterDead) {
            enemy = gp.getCurrentMonster();
            try {
                arrow = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/Arrow.png"));
                fightSelect = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/FightSelect.png"));
                moveList = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/MoveList.png"));
                redArrow = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/Redarrow.png"));
                textBox = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/Textbox.png"));
                enemyImage = enemy.image;
            } catch (IOException e) {
                e.printStackTrace();
            }

            drawBackground();

            if (battleNum == 0) {
                g2.drawImage(textBox, 0, gp.screenHeight - fightSelect.getHeight() * gp.scale, textBox.getWidth() * gp.scale, textBox.getHeight() * gp.scale, null);
                g2.drawImage(fightSelect, gp.screenWidth - fightSelect.getWidth() * gp.scale, gp.screenHeight - fightSelect.getHeight() * gp.scale, fightSelect.getWidth() * gp.scale, fightSelect.getHeight() * gp.scale, null);
                g2.drawImage(arrow, gp.screenWidth - (fightSelect.getWidth() * gp.scale) + 33, gp.screenHeight - (fightSelect.getHeight() * gp.scale) + 75, arrow.getWidth() * gp.scale, arrow.getHeight() * gp.scale, null);
            } else if (battleNum == 1) {

            } else if (battleNum == 2) {

            }

            if (enemy.life < 0) {
                enemy.life = 0;
                gp.monsterDead = true;
                gp.gameState = gp.playState;
            }
        }

    }

    public void drawBackground() {
        if (gp.tileM.getCount() <= 4) {
            try {
                background1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/cave1.png"));
                background2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/cave2.png"));
                background3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/cave3.png"));
                background4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/cave4.png"));
                background5 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/cave5.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (gp.tileM.getCount() <= 7) {
            try {
                background1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/mountain3.png"));
                background2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/mountain2.png"));
                background3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/mountain1.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (gp.tileM.getCount() <= 9) {
            try {
                background1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/hill1.png"));
                background2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/hill2.png"));
                background3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/hill3.png"));
                background4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/hill4.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                background1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/temple1.png"));
                background2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/temple2.png"));
                background3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/temple3.png"));
                background4 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/temple4.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        g2.drawImage(background1, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(background2,0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(background3,0, 0, gp.screenWidth, gp.screenHeight, null);
        if (background4 != null) {
            g2.drawImage(background4, 0,0, gp.screenWidth, gp.screenHeight, null);
        }
        if (background5 != null) {
            g2.drawImage(background5, 0,0, gp.screenWidth, gp.screenHeight, null);
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


    public void drawHealth() {
        int screenX = gp.tileSize / 2;
        int screenY = gp.tileSize / 2;

        g2.drawImage(heart, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.setColor(Color.black);
        g2.fillRoundRect(screenX + gp.tileSize, screenY + 9, gp.player.maxLife, gp.tileSize - 18, 35, 35);
        g2.setColor(Color.white);
        g2.fillRoundRect(screenX + gp.tileSize + 5, screenY + 14, gp.player.maxLife - 10, gp.tileSize - 28, 25, 25);
        g2.setColor(Color.red);
        g2.fillRoundRect(screenX + gp.tileSize + 5, screenY + 14, gp.player.life - 10, gp.tileSize - 28, 25, 25);
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
        return gp.screenWidth / 2 - length / 2;
    }


}
