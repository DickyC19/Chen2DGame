package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_EldenBeast extends Enemy{

    public ENEMY_EldenBeast(GamePanel gp) {
        super(gp);
        name = "EldenBeast";
        maxLife = 300 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];

        setImage("EldenBeast");
        setMoves();

        solidArea = new Rectangle(0, 0, image.getWidth() * 2, image.getHeight() * 2);
        x = gp.screenWidth / 5;
        y = - gp.tileSize;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Divine Punishment", 70, 6, 1, 0);
        moves[1] = new Move("Elden Stars", 80, 6, 1, 0);
        moves[2] = new Move("Wave of Gold", 100, 6, 2, 0);
    }




}
