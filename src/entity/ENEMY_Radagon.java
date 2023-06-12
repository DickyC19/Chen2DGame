package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_Radagon extends Enemy{

    public ENEMY_Radagon(GamePanel gp) {
        super(gp);
        name = "Radagon";
        maxLife = 275 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];

        setImage("Radagon");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 2, image.getHeight() * 2);
        x = gp.screenWidth / 3;
        y = gp.tileSize;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Holy Spear", 65, 6, 1, 0);
        moves[1] = new Move("Gold Breaker", 75, 6, 1, 0);
        moves[2] = new Move("Elden Shattering", 95, 6, 2, 0);
    }




}
