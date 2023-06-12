package main;

import entity.Enemy;
import entity.Entity;
import entity.Move;
import object.OBJ_Heart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UI {

    BufferedImage heart;
    BufferedImage arrow;
    BufferedImage fightSelect;
    BufferedImage moveList;
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
    boolean enemyDrawn;
    int currentHp;
    int enemyHp;
    boolean inFight1;
    boolean inFight2;
    boolean hadPlayerTurn;
    boolean hadEnemyTurn;
    boolean hasWon;
    boolean gainedSouls;
    boolean hasHealed;
    int damage;
    int enemyY;
    int count;

    String fightText;

    public boolean enemyAttacker;

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
        enemyDrawn = false;
        inFight1 = false;
        inFight2 = false;

        try {
            arrow = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("fight/Arrow.png")));
            fightSelect = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("fight/FightSelect.png")));
            moveList = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("fight/MoveList.png")));
            textBox = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("fight/Textbox.png")));
            InputStream is = getClass().getClassLoader().getResourceAsStream("fonts/firered.ttf");
            assert is != null;
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
        if (!inFight1 && !inFight2 && gp.gameState == gp.pauseState && !hasWon && !gainedSouls && !hasHealed) {
            drawBattleScene();
        } else if (hasHealed) {
            playHealAnimation();
        } else if (inFight2 || inFight1) {
            playAnimation(enemyAttacker);
        } else if (hasWon) {
            playWinAnimation();
        } else if (gainedSouls) {
            playSoulGainAnimation();
        }
        if (gp.gameState == gp.dialogueState) {
            drawHealth();
            drawDialogueScreen();
        }
        if (gp.gameState == gp.deathState) {
            drawGameOver();
        }
        if (gp.gameState == gp.winState) {
            drawWinScreen();
        }
        if (gp.gameState == gp.tradeState) {
            if (commandNum == -1) {
                drawDialogueScreen();
            }
            drawTradeMenu();
        }
    }

    public void drawTitleScreen() {
        g2.setFont(fireRed);
        String text = "(not) Elden Ring";
        int textX = getXCenteredText(text);
        int textY = gp.tileSize * 2;
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/title1.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert image != null;
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
    private void drawGameOver() {
        g2.setFont(fireRed);
        String text = "Game Over";
        int textX = getXCenteredText(text);
        int textY = gp.tileSize * 2;

        g2.setColor(new Color(100, 0, 0));
        g2.drawString(text, textX + 5, textY + 5);
        g2.setColor(Color.RED);
        g2.drawString(text, textX, textY);

        g2.setFont(fireRed.deriveFont(48F));
        text = "PLAY AGAIN";
        textX = getXCenteredText((text));
        textY += gp.tileSize * 9;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - gp.tileSize, textY);
        }

        text = "QUIT";
        textX = getXCenteredText((text));
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - gp.tileSize, textY);
        }
    }

    private void drawWinScreen() {
        g2.setFont(fireRed);
        String text = "YOU WIN";
        int textX = getXCenteredText(text);
        int textY = gp.tileSize * 2;

        g2.setColor(new Color(139, 128, 0));
        g2.drawString(text, textX + 5, textY + 5);
        g2.setColor(Color.YELLOW);
        g2.drawString(text, textX, textY);

        g2.setFont(fireRed.deriveFont(48F));
        text = "PLAY AGAIN";
        textX = getXCenteredText((text));
        textY += gp.tileSize * 9;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - gp.tileSize, textY);
        }

        text = "QUIT";
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
            drawCharacter();
            drawEnemy();
            drawFightHealth();

            if (battleNum == 0) {
                drawChoiceMenu();
                drawText();
            } else if (battleNum == 1) {
                drawBattleMenu();
            } else if (battleNum == 2) {
                drawTextBox();
                drawFight();
            } else if (battleNum == 3) {
                drawPotionMenu();
            } else if (battleNum == 4) {
                drawTextBox();
                drawPotionFight();
            }
        }
    }

    private void drawTextBox() {
        g2.drawImage(textBox, 0, gp.screenHeight - fightSelect.getHeight() * gp.scale, gp.screenWidth, textBox.getHeight() * gp.scale, null);
    }
    private void drawText() {
        int y;
        g2.setFont(fireRed.deriveFont(40F));
        y = gp.screenHeight - gp.tileSize * 2 + 20;
        currentDialogue = "What  will\nPLAYER  do?";
        for (String line : currentDialogue.split("\n")) {
            g2.setColor(Color.darkGray);
            g2.drawString(line, gp.tileSize / 2 + 19, y + 3);
            g2.setColor(Color.white);
            g2.drawString(line, gp.tileSize / 2 + 15, y);
            y += 65;
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

    private void drawCharacter() {
        g2.drawImage(character, gp.tileSize, gp.screenHeight - gp.tileSize * 9, character.getWidth() * gp.scale, character.getHeight() * gp.scale, null);
    }

    private void drawEnemy() {
        if (enemy.name.equals("RedWolfOfRadagon")) {
            g2.drawImage(enemyImage, gp.tileSize * 6 + 60, -gp.tileSize * 6 + 12, enemyImage.getWidth() * 3, enemyImage.getHeight() * 3, null);
        } else {
            g2.drawImage(enemyImage, gp.tileSize * 6 + 60, -gp.tileSize * 2 + 12, enemyImage.getWidth() * 3, enemyImage.getHeight() * 3, null);
        }
        enemyDrawn = true;
    }

    private void drawEnemyFalling(int y) {
        if (enemy.name.equals("RedWolfOfRadagon")) {
            g2.drawImage(enemyImage, gp.tileSize * 6 + 60, -gp.tileSize * 6 + 12 + y, enemyImage.getWidth() * 3, enemyImage.getHeight() * 3, null);
        } else {
            g2.drawImage(enemyImage, gp.tileSize * 6 + 60, -gp.tileSize * 2 + 12 + y, enemyImage.getWidth() * 3, enemyImage.getHeight() * 3, null);
        }
        enemyDrawn = true;
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
        g2.fillRect(screenX + gp.tileSize, screenY + 9, gp.player.maxLife, gp.tileSize - 18);
        g2.setColor(Color.white);
        g2.fillRect(screenX + gp.tileSize + 5, screenY + 14, gp.player.maxLife - 10, gp.tileSize - 28);
        g2.setColor(Color.red);
        g2.fillRect(screenX + gp.tileSize + 5, screenY + 14, gp.player.life - 10, gp.tileSize - 28);
    }

    private void drawSubWindow(int x, int y, int width, int height, Graphics2D g2) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    private void drawChoiceMenu() {
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

    private void drawBattleMenu() {
        int x = 0;
        int y = 0;
        g2.setFont(fireRed.deriveFont(40F));
        g2.setColor(Color.black);
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
        g2.setFont(fireRed.deriveFont(37F));
        g2.setColor(Color.black);
        g2.drawString(String.valueOf(gp.player.moves[choiceNum].power), gp.screenWidth - (gp.tileSize * 5) / 2, gp.screenHeight - gp.tileSize - 38);
        g2.drawString(gp.player.moves[choiceNum].type,gp.screenWidth - (gp.tileSize * 6) / 2,gp.screenHeight - gp.tileSize + 24);

    }

    private void drawPotionMenu() {
        int x = 0;
        int y = 0;
        g2.setFont(fireRed.deriveFont(40F));
        g2.setColor(Color.black);
        g2.drawImage(moveList, 0, gp.screenHeight - moveList.getHeight() * gp.scale, gp.screenWidth, moveList.getHeight() * gp.scale, null);
        g2.drawString("Potion   x" + gp.player.potions, gp.tileSize / 2 + gp.tileSize - 30, gp.screenHeight - gp.tileSize - 15);
        g2.drawString("mpPotion  x" + gp.player.mpPotions, gp.tileSize * 6 + 32, gp.screenHeight - gp.tileSize - 15);
        g2.setFont(fireRed.deriveFont(37F));
        g2.setColor(Color.black);
        if (choiceNum == 0) {
            x = gp.tileSize - 30;
            y = gp.screenHeight - gp.tileSize - 55;
            g2.drawString("100", gp.screenWidth - (gp.tileSize * 5) / 2, gp.screenHeight - gp.tileSize - 38);

        } else if (choiceNum == 1){
            x = gp.tileSize * 6;
            y = gp.screenHeight - gp.tileSize - 55;
            g2.drawString("10", gp.screenWidth - (gp.tileSize * 5) / 2, gp.screenHeight - gp.tileSize - 38);
        }
        g2.drawString("Item",gp.screenWidth - (gp.tileSize * 6) / 2,gp.screenHeight - gp.tileSize + 24);
        g2.drawImage(arrow, x, y, arrow.getWidth() * gp.scale, arrow.getHeight() * gp.scale, null);
    }

    private void drawFightText(String dialogue) {
        int y;
        g2.setFont(fireRed.deriveFont(40F));
        currentDialogue = dialogue;
        y = gp.screenHeight - gp.tileSize * 2 + 20;
        for (String line : currentDialogue.split("\n")) {
            g2.setColor(Color.darkGray);
            g2.drawString(line, gp.tileSize / 2 + 19, y + 3);
            g2.setColor(Color.white);
            g2.drawString(line, gp.tileSize / 2 + 15, y);
            y += 65;
        }
    }

    private void drawFight() {
        Move move;
        currentHp = gp.player.life;
        enemyHp = enemy.life;

        if (!inFight2 && !hadPlayerTurn) {
            move = gp.player.moves[choiceNum];
            fightText = "PLAYER  used\n" + move.name;

            drawFightText(fightText);

            damageCalc(gp.player, move);
            hadPlayerTurn = false;
            enemyHp = enemy.life - damage;
            gp.player.mana -= move.cost;
            playAnimation(false);
        }

        if (enemy.life == 0) {
            hadPlayerTurn = false;
            hadEnemyTurn = false;
            inFight2 = false;
            inFight1 = false;
            battleNum = 0;
            choiceNum = 0;
            if (enemy.name.equals("BeastClergyMan") || enemy.name.equals("Radagon")) {
                enemy = gp.monsters[gp.tileM.getCount()];
                setImages();
            } else {
                playWinAnimation();
            }
        }

        if (!inFight1 && !hadEnemyTurn && enemyHp != 0) {
            // enemy's move
            move = enemy.determineMove();

            fightText = enemy.name + "  used\n" + move.name;
            drawFightText(fightText);

            damageCalc(enemy, move);
            hadEnemyTurn = false;
            currentHp = gp.player.life - damage;

            playAnimation(true);
        }
        if (gp.player.life == 0) {
            currentHp = 0;
            battleNum = 0;
            choiceNum = 0;
            commandNum = 0;
            gp.gameState = gp.deathState;
        }

        if (hadEnemyTurn && hadPlayerTurn) {
            hadPlayerTurn = false;
            hadEnemyTurn = false;
            inFight2 = false;
            inFight1 = false;
            battleNum = 0;
            choiceNum = 0;
        }
    }

    private void drawPotionFight() {
        Move move;
        currentHp = gp.player.life;
        enemyHp = enemy.life;

        if (!inFight2 && !hadPlayerTurn) {
            fightText = "PLAYER  used\na  potion";
            drawFightText(fightText);

            hadPlayerTurn = false;
            if (choiceNum == 0) {
                currentHp = gp.player.life + 100 * gp.player.attack;
                gp.player.potions --;
            } else if (choiceNum == 1) {
                currentHp = gp.player.mana + 10;
                gp.player.mpPotions --;
            }
            playHealAnimation();
        }

        if (!inFight1 && !hadEnemyTurn && enemyHp != 0) {
            // enemy's move
            move = enemy.determineMove();

            fightText = enemy.name + "  used\n" + move.name;
            drawFightText(fightText);

            damageCalc(enemy, move);
            hadEnemyTurn = false;
            currentHp = gp.player.life - damage;

            playAnimation(true);
        }
        if (gp.player.life == 0) {
            currentHp = 0;
            battleNum = 0;
            choiceNum = 0;
            commandNum = 0;
            gp.gameState = gp.deathState;
        }

        if (hadEnemyTurn && hadPlayerTurn) {
            hadPlayerTurn = false;
            hadEnemyTurn = false;
            inFight2 = false;
            inFight1 = false;
            battleNum = 0;
            choiceNum = 0;
        }
    }

    private void playAnimation(boolean boss) {
        drawBackground();
        drawCharacter();
        drawEnemy();
        drawFightHealth();
        drawTextBox();
        drawFightText(fightText);

        if (currentHp < 0) {
            currentHp = 0;
        }
        if (enemyHp < 0) {
            enemyHp = 0;
        }

        if (boss) {
            inFight2 = true;
            enemyAttacker = true;
            if (gp.player.life > currentHp) {
                System.out.println(gp.player.life);
                System.out.println(currentHp);
                gp.player.life -= 1;
            } else {
                inFight2 = false;
                hadEnemyTurn = true;
            }

        } else {
            inFight1 = true;
            enemyAttacker = false;
            if (enemy.life > enemyHp) {
                System.out.println(enemy.life);
                System.out.println(enemyHp);
                enemy.life -= 1;
            } else {
                inFight1 = false;
                hadPlayerTurn = true;
            }
        }
    }

    private void playWinAnimation() {
        hasWon = true;
        drawBackground();
        drawCharacter();
        drawEnemyFalling(enemyY);
        drawFightHealth();
        drawTextBox();
        drawFightText(enemy.name + "  has\nperished");

        if (enemyY < 150) {
            enemyY += 4;
        } else {
            enemyY = 0;
            hasWon = false;
            playSoulGainAnimation();
        }
    }

    private void playSoulGainAnimation() {
        gainedSouls = true;
        drawBackground();
        drawCharacter();
        drawFightHealth();
        drawTextBox();
        drawFightText("PLAYER  gained\n" + enemy.souls + "  Souls");

        if (count < 150) {
            count++;
        } else {
            count = 0;
            gainedSouls = false;
            enemyHp = 0;
            gp.player.souls += enemy.souls;
            gp.monsterDead = true;
            gp.gameState = gp.playState;
        }
    }

    private void playHealAnimation() {
        hasHealed = true;
        drawBackground();
        drawCharacter();
        drawEnemy();
        drawFightHealth();
        drawTextBox();
        drawFightText(fightText);

        inFight1 = true;
        if (choiceNum == 0) {
            if (currentHp > gp.player.maxLife) {
                currentHp = gp.player.maxLife;
            }
            if (gp.player.life < currentHp) {
                gp.player.life += 1;
            } else {
                inFight1 = false;
                hadPlayerTurn = true;
                hasHealed = false;
            }
        } else if (choiceNum == 1) {
            if (currentHp > gp.player.maxMana) {
                currentHp = gp.player.maxMana;
            }
            if (gp.player.mana < currentHp) {
                gp.player.mana += 1;
            } else {
                inFight1 = false;
                hadPlayerTurn = true;
                hasHealed = false;
            }
        }
    }
// al;kdfjasl;dfjlkasjfkl;jaslkfjsdakl;jfaldksjl;daskfjkl;fadjl;kasdfjlk;asdfjl;kasdfj
    // ;lsfadjkl;sajfkl;adsjklf;j;lajkl;fajsl;kjfl;ksadjflas/
    // fldsajfl;asjl;fdjsal;kfjalk;jfalsdk;jlska
    // win screeen eeeeeeneeeeen
    /// theeene back grorusdan

    private void damageCalc(Entity entity, Move move) {
        if (Math.random() <= move.critRate / 100.0) {
            damage = move.power * entity.attack * 2;
        } else {
            damage = move.power * entity.attack;
        }
    }

    private void drawFightHealth() {
        int screenX = gp.tileSize / 2;
        int screenY = gp.tileSize / 2;

        // Player hp and mp
        g2.drawImage(heart, gp.screenWidth / 2 - gp.tileSize, gp.screenHeight / 2 + screenY, gp.tileSize, gp.tileSize, null);
        g2.setColor(Color.black);
        g2.fillRect(gp.screenWidth / 2, gp.screenHeight / 2 + screenY + 9, 510, gp.tileSize - 18);
        g2.setColor(Color.white);
        g2.fillRect(gp.screenWidth / 2 + 5, gp.screenHeight / 2 + screenY+ 14, 500, gp.tileSize - 28);
        g2.setColor(Color.red);
        g2.fillRect(gp.screenWidth / 2 + 5, gp.screenHeight / 2 + screenY + 14, (int) (500 * ((double) gp.player.life / gp.player.maxLife)), gp.tileSize - 28);

        g2.setColor(Color.black);
        g2.fillRect(gp.screenWidth / 2, gp.screenHeight / 2 + gp.tileSize + 20, 410, gp.tileSize - 18);
        g2.setColor(Color.white);
        g2.fillRect(gp.screenWidth / 2 + 5, gp.screenHeight / 2 + gp.tileSize + 25, 400, gp.tileSize - 28);
        g2.setColor(new Color(96,176,244));
        g2.fillRect(gp.screenWidth / 2 + 5, gp.screenHeight / 2 + gp.tileSize + 25, (int) (400 * ((double) gp.player.mana / gp.player.maxMana)), gp.tileSize - 28);

        // Enemy Name and HP
        g2.setFont(fireRed.deriveFont(40F));
        g2.setColor(new Color(139, 128, 0));
        g2.drawString(enemy.name, screenX + 3, screenY + 18 + 30);
        g2.setColor(Color.YELLOW);
        g2.drawString(enemy.name, screenX, screenY + 15 + 30);

        g2.setColor(Color.black);
        g2.fillRect(screenX - 8, 10 + screenY + 9 + 30, 410, gp.tileSize - 18);
        g2.setColor(Color.white);
        g2.fillRect(screenX - 3, 10 + screenY + 14 + 30, 400, gp.tileSize - 28);
        g2.setColor(Color.red);
        g2.fillRect(screenX - 3, 10 + screenY + 14 + 30, (int) (400 * ((double) enemy.life / enemy.maxLife)), gp.tileSize - 28);
    }

    private void drawTradeMenu() {
        if (commandNum != -1) {
            drawItemDescription(gp.merchant.items[commandNum]);
        }
        int dialogueX = gp.tileSize * 13;
        int dialogueY = gp.tileSize * 4;
        int dialogueWidth = (int) (gp.tileSize * 3.5);
        int dialogueHeight = (int) (gp.tileSize * 3.5);

        drawSubWindow(dialogueX, dialogueY, dialogueWidth, dialogueHeight, g2);
        drawSoulCount();
        g2.setFont(fireRed.deriveFont(28F));
        dialogueX += gp.tileSize / 2 + 24;
        dialogueY += gp.tileSize / 2 + 24;

        String options = "Health\nMana\nAttack\nPotion\nMpPotion";
        for (String line : options.split("\n")) {
            g2.drawString(line, dialogueX, dialogueY);
            dialogueY += 35;
        }

        dialogueX -= gp.tileSize;
        if (commandNum == 0) {
            dialogueY -= 175;
        } else if (commandNum == 1){
            dialogueY -= 140;
        } else if (commandNum == 2) {
            dialogueY -= 105;
        } else if (commandNum == 3) {
            dialogueY -= 70;
        } else if (commandNum == 4) {
            dialogueY -= 35;
        }
        dialogueX += gp.tileSize * 2 - 24;
        if (commandNum != -1) {
            g2.drawString(">", dialogueX - gp.tileSize, dialogueY);
        }
    }

    private void drawItemDescription(String text) {
        int dialogueX = gp.tileSize * 2;
        int dialogueY = gp.tileSize / 2;
        int dialogueWidth = gp.screenWidth - (gp.tileSize * 4);
        int dialogueHeight = gp.tileSize * 5;

        drawSubWindow(dialogueX, dialogueY, dialogueWidth, dialogueHeight, g2);

        g2.setFont(fireRed.deriveFont(28F));
        dialogueX += gp.tileSize;
        dialogueY += gp.tileSize;

        for (String line : text.split("\n")) {
            g2.drawString(line, dialogueX, dialogueY);
            dialogueY += 40;
        }
    }

    private void drawSoulCount() {
        int dialogueX = gp.tileSize * 2;
        int dialogueY = (int) (gp.tileSize * 5.4);
        int dialogueWidth = gp.tileSize * 4;
        int dialogueHeight = gp.tileSize;

        drawSubWindow(dialogueX, dialogueY, dialogueWidth, dialogueHeight, g2);

        g2.setFont(fireRed.deriveFont(28F));
        dialogueX += 20;
        dialogueY += 45;

        g2.drawString("Souls: " + gp.player.souls, dialogueX, dialogueY);
    }

    public int getXCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    public void setImages() {
        if (gp.tileM.getCount() <= 4) {
            try {

                background1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/mountain3.png")));
                background2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/mountain2.png")));
                background3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/mountain1.png")));
                background4 = null;
                background5 = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (gp.tileM.getCount() <= 7) {
            try {
                background1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/hill1.png")));
                background2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/hill2.png")));
                background3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/hill3.png")));
                background4 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/hill4.png")));
                background5 = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (gp.tileM.getCount() <= 9) {
            try {
                background1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/cave1.png")));
                background2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/cave2.png")));
                background3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/cave3.png")));
                background4 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/cave4.png")));
                background5 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/cave5.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                background1 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/temple1.png")));
                background2 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/temple2.png")));
                background3 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/temple3.png")));
                background4 = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("backgrounds/temple4.png")));
                background5 = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            enemyImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("enemies/" + enemy.name + ".png")));
            character = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/pixelknight.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
