package main;

import Object.*;

public class AssetSetter {

    private GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.setObjectAt(0,new Obj_Key());
        gp.getObjectAt(0).setX(23*gp.getTileSize());
        gp.getObjectAt(0).setY(7*gp.getTileSize());

        gp.setObjectAt(1,new Obj_Key());
        gp.getObjectAt(1).setX(23*gp.getTileSize());
        gp.getObjectAt(1).setY(40*gp.getTileSize());

        gp.setObjectAt(2,new Obj_Key());
        gp.getObjectAt(2).setX(38*gp.getTileSize());
        gp.getObjectAt(2).setY(8*gp.getTileSize());

        gp.setObjectAt(3,new Obj_Door());
        gp.getObjectAt(3).setX(10*gp.getTileSize());
        gp.getObjectAt(3).setY(11*gp.getTileSize());

        gp.setObjectAt(4,new Obj_Door());
        gp.getObjectAt(4).setX(8*gp.getTileSize());
        gp.getObjectAt(4).setY(28*gp.getTileSize());

        gp.setObjectAt(5,new Obj_Door());
        gp.getObjectAt(5).setX(12*gp.getTileSize());
        gp.getObjectAt(5).setY(22*gp.getTileSize());

        gp.setObjectAt(6,new Obj_Chest());
        gp.getObjectAt(6).setX(10*gp.getTileSize());
        gp.getObjectAt(6).setY(7*gp.getTileSize());
    }
}
