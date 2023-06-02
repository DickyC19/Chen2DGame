package main;

import entity.Entity;
import entity.NPC_OldMan;

public class CollisionChecker {

    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entityLeftX + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entityTopY + entity.solidArea.height;

        int entityLeftCol = entityLeftX / gp.tileSize;
        int entityRightCol = entityRightX / gp.tileSize;
        int entityTopRow = entityTopY / gp.tileSize;
        int entityBottomRow = entityBottomY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
                if (gp.tileM.mapTileNum.length > entityRightCol) {
                    tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                    if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                        entity.collisionOn = true;
                    }
                }
            }
        }
    }

    public boolean checkEntity(Entity entity, Entity target) {
        boolean collided = false;
        entity.solidArea.x = entity.x + entity.solidArea.x;
        entity.solidArea.y = entity.y + entity.solidArea.y;

        target.solidArea.x = target.x + target.solidArea.x;
        target.solidArea.y = target.y + target.solidArea.y;

        switch (entity.direction) {
            case "up" -> {
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(target.solidArea)) {
                    entity.collisionOn = true;
                    collided = true;
                }
            }
            case "down" -> {
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(target.solidArea)) {
                    entity.collisionOn = true;
                    collided = true;
                }
            }
            case "left" -> {
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(target.solidArea)) {
                    entity.collisionOn = true;
                    collided = true;
                }
            }
            case "right" -> {
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(target.solidArea)) {
                    entity.collisionOn = true;
                    collided = true;
                }
            }
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        target.solidArea.x = target.solidAreaDefaultX;
        target.solidArea.y = target.solidAreaDefaultY;
        return collided;
    }



}
