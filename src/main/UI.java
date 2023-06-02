package main;

import entity.Enemy;
import entity.Entity;
import object.OBJ_Heart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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
    BufferedImage character;
    GamePanel gp;
    Graphics2D g2;

    public Enemy enemy;
    public int commandNum = 0;
    public int battleNum = 0;
    public int choiceNum = 0;
    public Font fireRed;
    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        Entity heartObj = new OBJ_Heart(gp);
        heart = heartObj.image;
        enemy = null;

        try {
            arrow = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/Arrow.png"));
            fightSelect = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/FightSelect.png"));
            moveList = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/MoveList.png"));
            redArrow = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/Redarrow.png"));
            textBox = ImageIO.read(getClass().getClassLoader().getResourceAsStream("fight/Textbox.png"));
            InputStream is = getClass().getClassLoader().getResourceAsStream("fonts/firered.ttf");
            fireRed = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(96F);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
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
        g2.setFont(fireRed);
        String text = "(not) Elden Ring";
        int textX = getXCenteredText(text);
        int textY = gp.tileSize * 2;
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("backgrounds/title1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(image, gp.screenWidth / 4, gp.tileSize * 3 - 55, image.getWidth(), image.getHeight(), null);

        g2.setColor(new Color(139, 128, 0));
        g2.drawString(text, textX + 5, textY + 5);
        g2.setColor(Color.YELLOW);
        g2.drawString(text, textX, textY);

        g2.setFont(fireRed.deriveFont(48F));
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

            drawBackground();
            drawCharacters();

            if (battleNum == 0) {
                drawChoiceMenu();
            } else if (battleNum == 1) {
                drawBattleMenu();
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

    public void drawCharacters() {
        // fix coords

        g2.drawImage(character, gp.tileSize, gp.screenHeight - gp.tileSize * 9, character.getWidth() * gp.scale, character.getHeight() * gp.scale, null);
        g2.drawImage(enemyImage, gp.tileSize * 6 + 60, -gp.tileSize * 2 + 12, enemyImage.getWidth() * 3, enemyImage.getHeight() * 3, null);
    }

    public void drawDialogueScreen() {
        int dialogueX = gp.tileSize * 2;
        int dialogueY = gp.tileSize / 2;
        int dialogueWidth = gp.screenWidth - (gp.tileSize * 4);
        int dialogueHeight = gp.tileSize * 5;

        drawSubWindow(dialogueX, dialogueY, dialogueWidth, dialogueHeight, g2);

        g2.setFont(fireRed.deriveFont(28F));
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

    public void drawChoiceMenu() {
        int x = 0;
        g2.drawImage(textBox, 0, gp.screenHeight - fightSelect.getHeight() * gp.scale, textBox.getWidth() * gp.scale, textBox.getHeight() * gp.scale, null);
        g2.drawImage(fightSelect, gp.screenWidth - fightSelect.getWidth() * gp.scale, gp.screenHeight - fightSelect.getHeight() * gp.scale, fightSelect.getWidth() * gp.scale, fightSelect.getHeight() * gp.scale, null);
        if (choiceNum == 0) {
            x = gp.screenWidth - (fightSelect.getWidth() * gp.scale) + 33;
        } else if (choiceNum == 1){
            x = gp.screenWidth - gp.tileSize * 3 - 30;
        }
        g2.drawImage(arrow, x, gp.screenHeight - (fightSelect.getHeight() * gp.scale) + 75, arrow.getWidth() * gp.scale, arrow.getHeight() * gp.scale, null);
    }

    public void drawBattleMenu() {
        int x = 0;
        int y = 0;
        g2.setFont(fireRed.deriveFont(40F));
        g2.drawImage(moveList, 0, gp.screenHeight - moveList.getHeight() * gp.scale, gp.screenWidth, moveList.getHeight() * gp.scale, null);
        for (int i = 0; i < gp.player.moves.length / 2; i ++) {
            g2.drawString(gp.player.moves[i].name, gp.tileSize, gp.screenHeight - gp.tileSize * (2 - i) + 15);
        }
        for (int i = 0; i < gp.player.moves.length / 2; i ++) {
            g2.drawString(gp.player.moves[i + 2].name, gp.tileSize * 6, gp.screenHeight - gp.tileSize * (2 - i) + 15);
        }

        if (choiceNum == 0) {
            x = gp.tileSize - 30;
            y = gp.screenHeight - gp.tileSize * 2 - 27;
        } else if (choiceNum == 1){
            x = gp.tileSize - 30;
            y = gp.screenHeight - gp.tileSize - 27;
        } else if (choiceNum == 2) {
            x = gp.tileSize * 6 - 30;
            y = gp.screenHeight - gp.tileSize * 2 - 27;
        } else if (choiceNum == 3) {
            x = gp.tileSize * 6 - 30;
            y = gp.screenHeight - gp.tileSize - 27;
        }
        g2.drawImage(arrow, x, y, arrow.getWidth() * gp.scale, arrow.getHeight() * gp.scale, null);
    }


    public int getXCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    public void setImages() {
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

        try {
            enemyImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("enemies/" + enemy.name + ".png"));
            character = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/pixelknight.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
