package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    KeyHandler keyH;
    String previousDirection;
    public int potions;
    public int mpPotions;
    public int souls;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        solidArea = new Rectangle(21, 47, 22, 16);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        moves = new Move[4];
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = gp.tileSize;
        y = gp.tileSize * 6;
        speed = 4;
        direction = "right";
        previousDirection = "right";

        maxLife = 300;
        life = maxLife;
        maxMana = 15;
        mana = maxMana;
        potions = 1;
        mpPotions = 0;

        moves[0] = new Move("Slash", 40, 6, 1, 0);
        moves[1] = new Move("Heavy Slash", 70, 12, 2, 1);
        moves[2] = new Move("Lion's Claw", 100, 15, 1, 3);
        moves[3] = new Move("EarthShaker", 120, 30, 2, 5);
        attack = 1;
    }

    public void updateValues(int health, int mana, int attack, int potions, int manaPotions) {
        maxLife += health;
        maxMana += mana;
        this.attack += attack;
        this.potions += potions;
        mpPotions += manaPotions;
    }

    public void getPlayerImage() {
        try {
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Left1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Left2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Right1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/Right2.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
            if (keyH.upPressed) {
                if (direction.equals("right")) {
                    previousDirection = "right";
                } else if (direction.equals("left")) {
                    previousDirection = "left";
                }
                direction = "up";
            } else if (keyH.downPressed) {
                if (direction.equals("right")) {
                   previousDirection = "right";
                } else if (direction.equals("left")) {
                    previousDirection = "left";
                }
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            if (gp.oldMan.isDrawn) {
                interactNPC(gp.collisionChecker.checkEntity(this, gp.oldMan));
            }

            if (gp.merchant.isDrawn) {
                gp.ui.commandNum = -1;
                interactNPC(gp.collisionChecker.checkEntity(this, gp.merchant));
            }

            if (!gp.monsterDead) {
                gp.ui.battleNum = 0;
                gp.ui.choiceNum = 0;
                gp.ui.commandNum = 0;
                interactEnemy(gp.collisionChecker.checkEntity(this, gp.monsters[0]));
            }

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up", "down" -> {
                if (previousDirection.equals("right")) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                } else {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }

        g2.drawImage(image, x, y, gp.tileSize , gp.tileSize, null);
    }

    public void interactNPC(boolean collided) {
        if (collided) {
            if (gp.merchant.isDrawn) {
                if (gp.keyH.spacePressed) {
                    gp.merchant.speak();
                }
            } else {
                if (gp.keyH.spacePressed) {
                    gp.gameState = gp.dialogueState;
                    gp.oldMan.speak();
                }
            }
        }
        gp.keyH.spacePressed = false;
    }

    public void interactEnemy(boolean collided) {
        if (collided) {
            gp.gameState = gp.pauseState;
        }
    }


}
