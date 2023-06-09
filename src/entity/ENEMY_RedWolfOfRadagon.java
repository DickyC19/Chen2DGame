package entity;

import main.GamePanel;

import java.awt.*;

public class ENEMY_RedWolfOfRadagon extends Enemy{

    public ENEMY_RedWolfOfRadagon(GamePanel gp) {
        super(gp);
        name = "RedWolfOfRadagon";
        maxLife = 150 * gp.difficulty;
        life = maxLife;
        attack = gp.difficulty;
        moves = new Move[3];
        souls = 200 * gp.difficulty;
        setImage("RedWolfOfRadagon");
        setMoves();

        solidArea = new Rectangle(0, 0, (int) (image.getWidth() * 1.5) , (int) (image.getHeight() * 1.5));
        x = gp.screenWidth / 3;
        y = - gp.tileSize;
        solidAreaDefaultX = 0;
        solidAreaDefaultY = 0;
    }

    public void setMoves() {
        moves[0] = new Move("Blade Plunge", 40, 6, 1, 0);
        moves[1] = new Move("Magic Glintblade", 50, 6, 1, 0);
        moves[2] = new Move("Glintblade Dash", 70, 6, 2, 0);
    }




}
