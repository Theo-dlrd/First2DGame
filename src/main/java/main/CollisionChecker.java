package main;

import entity.Entity;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = (int) (entity.getX() + entity.getSolidArea().getX());
        int entityRightWorldX = (int) (entity.getX() + entity.getSolidArea().getX() + entity.getSolidArea().getWidth());
        int entityTopWorldY = (int) (entity.getY() + entity.getSolidArea().getY());
        int entityBottomWorldY = (int) (entity.getY() + entity.getSolidArea().getY() + entity.getSolidArea().getHeight());

        int entityLeftCol = entityLeftWorldX/gp.getTileSize();
        int entityRightCol = entityRightWorldX/gp.getTileSize();
        int entityTopRow = entityTopWorldY/gp.getTileSize();
        int entityBottomRow = entityBottomWorldY/gp.getTileSize();

        int tileNum1, tileNum2;

        switch (entity.getDirection()){
            case UP -> {
                entityTopRow = (int) ((entityTopWorldY - entity.getSpeed())/gp.getTileSize());
                tileNum1 = gp.getTileManager().getMapTileNumIn(entityLeftCol,entityTopRow);
                tileNum2 = gp.getTileManager().getMapTileNumIn(entityRightCol,entityTopRow);
                if(gp.getTileManager().getTileImageByIndex(tileNum1).isCollideable() || gp.getTileManager().getTileImageByIndex(tileNum2).isCollideable()){
                    entity.setCollisionOn(true);
                }
            }
            case DOWN -> {
                entityBottomRow = (int) ((entityBottomWorldY + entity.getSpeed())/gp.getTileSize());
                tileNum1 = gp.getTileManager().getMapTileNumIn(entityLeftCol,entityBottomRow);
                tileNum2 = gp.getTileManager().getMapTileNumIn(entityRightCol,entityBottomRow);
                if(gp.getTileManager().getTileImageByIndex(tileNum1).isCollideable() || gp.getTileManager().getTileImageByIndex(tileNum2).isCollideable()){
                    entity.setCollisionOn(true);
                }
            }
            case LEFT -> {
                entityLeftCol = (int) ((entityLeftWorldX - entity.getSpeed())/gp.getTileSize());
                tileNum1 = gp.getTileManager().getMapTileNumIn(entityLeftCol,entityTopRow);
                tileNum2 = gp.getTileManager().getMapTileNumIn(entityLeftCol,entityBottomRow);
                if(gp.getTileManager().getTileImageByIndex(tileNum1).isCollideable() || gp.getTileManager().getTileImageByIndex(tileNum2).isCollideable()){
                    entity.setCollisionOn(true);
                }
            }
            case RIGHT -> {
                entityRightCol = (int) ((entityRightWorldX - entity.getSpeed())/gp.getTileSize());
                tileNum1 = gp.getTileManager().getMapTileNumIn(entityRightCol,entityTopRow);
                tileNum2 = gp.getTileManager().getMapTileNumIn(entityRightCol,entityBottomRow);
                if(gp.getTileManager().getTileImageByIndex(tileNum1).isCollideable() || gp.getTileManager().getTileImageByIndex(tileNum2).isCollideable()){
                    entity.setCollisionOn(true);
                }
            }
        }
    }

    public int checkObject(Entity entity, boolean player){
        int index = -1;

        for (int i = 0; i < gp.getNbObjects(); i++) {
            if(gp.getObjectAt(i) != null){
                //Get entity's solid area position
                entity.getSolidArea().x = (int) (entity.getX() + entity.getSolidAreaDefaultX());
                entity.getSolidArea().y = (int) (entity.getY() + entity.getSolidAreaDefaultY());

                //Get the object's solid area position
                gp.getObjectAt(i).getSolidArea().x = (int) (gp.getObjectAt(i).getX() + gp.getObjectAt(i).getSolidArea().getX());
                gp.getObjectAt(i).getSolidArea().y = (int) (gp.getObjectAt(i).getY() + gp.getObjectAt(i).getSolidArea().getY());

                switch(entity.getDirection()){
                    case UP -> {
                        entity.getSolidArea().y -= (int) entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.getObjectAt(i).getSolidArea())){
                            if(gp.getObjectAt(i).isCollideable()){
                                entity.setCollisionOn(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                    }
                    case DOWN -> {
                        entity.getSolidArea().y += (int) entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.getObjectAt(i).getSolidArea())){
                            if(gp.getObjectAt(i).isCollideable()){
                                entity.setCollisionOn(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                    }
                    case LEFT -> {
                        entity.getSolidArea().x -= (int) entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.getObjectAt(i).getSolidArea())){
                            if(gp.getObjectAt(i).isCollideable()){
                                entity.setCollisionOn(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                    }
                    case RIGHT -> {
                        entity.getSolidArea().x += (int) entity.getSpeed();
                        if(entity.getSolidArea().intersects(gp.getObjectAt(i).getSolidArea())){
                            if(gp.getObjectAt(i).isCollideable()){
                                entity.setCollisionOn(true);
                            }
                            if(player){
                                index = i;
                            }
                        }
                    }
                }
                entity.getSolidArea().x = (int) entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = (int) entity.getSolidAreaDefaultY();
                gp.getObjectAt(i).getSolidArea().x = gp.getObjectAt(i).getSolidAreaDefaultX();
                gp.getObjectAt(i).getSolidArea().y = gp.getObjectAt(i).getSolidAreaDefaultY();
            }
        }

        return index;
    }
}
