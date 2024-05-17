package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    private final KeyHandler keyH;
    private final int screenX;
    private final int screenY;
    private int hasKey;


    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;
        this.hasKey = 0;

        screenX = gp.getScreenWidth()/2-gp.getTileSize()/2;
        screenY = gp.getScreenHeight()/2-gp.getTileSize()/2;

        setDefaultValues();
        getPlayerImages();
    }

    public void setDefaultValues(){
        super.setX(gp.getTileSize()*23);
        super.setY(gp.getTileSize()*21);
        super.setSpeed(gp.getWorldWidth()/600.0);
        super.setDirection(Direction.DOWN);
    }

    public void getPlayerImages(){
        try{
            super.setUp1Image(ImageIO.read(getClass().getResourceAsStream("/Player/Walking/boy_up_1.png")));
            super.setUp2Image(ImageIO.read(getClass().getResourceAsStream("/Player/Walking/boy_up_2.png")));
            super.setDown1Image(ImageIO.read(getClass().getResourceAsStream("/Player/Walking/boy_down_1.png")));
            super.setDown2Image(ImageIO.read(getClass().getResourceAsStream("/Player/Walking/boy_down_2.png")));
            super.setLeft1Image(ImageIO.read(getClass().getResourceAsStream("/Player/Walking/boy_left_1.png")));
            super.setLeft2Image(ImageIO.read(getClass().getResourceAsStream("/Player/Walking/boy_left_2.png")));
            super.setRight1Image(ImageIO.read(getClass().getResourceAsStream("/Player/Walking/boy_right_1.png")));
            super.setRight2Image(ImageIO.read(getClass().getResourceAsStream("/Player/Walking/boy_right_2.png")));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.isUpPressed() || keyH.isDownPressed() || keyH.isLeftPressed() || keyH.isRightPressed()) {
            if (keyH.isUpPressed()) {
                super.setDirection(Direction.UP);
            }
            if (keyH.isDownPressed()) {
                super.setDirection(Direction.DOWN);
            }
            if (keyH.isLeftPressed()) {
                super.setDirection(Direction.LEFT);
            }
            if (keyH.isRightPressed()) {
                super.setDirection(Direction.RIGHT);
            }

            setCollisionOn(false);
            gp.getCollisionChecker().checkTile(this);

            int objIndex = gp.getCollisionChecker().checkObject(this,true);
            pickUpObject(objIndex);

            if(!getCollision()){
                if(super.getDirection()==Direction.UP){
                    super.decrementY(super.getSpeed());
                }
                if(super.getDirection()==Direction.DOWN){
                    super.incrementY(super.getSpeed());
                }
                if(super.getDirection()==Direction.LEFT){
                    super.decrementX(super.getSpeed());
                }
                if(super.getDirection()==Direction.RIGHT){
                    super.incrementX(super.getSpeed());
                }

                super.incrementSpriteCounter();
                if (super.getSpriteCounter() > 12) {
                    if (super.getSpriteNum() == 1) {
                        super.incrementSpriteNum();
                    } else {
                        super.decrementSpriteNum();
                    }
                    super.razSprinteCounter();
                }
            }
        }
    }

    public void pickUpObject(int i){
        if(i>=0){
            String objName = gp.getObjectAt(i).getName();
            switch (objName){
                case "Key" -> {
                    hasKey++;
                    gp.setObjectAt(i,null);
                }
                case "Door" -> {
                    if(hasKey>0){
                        hasKey--;
                        gp.setObjectAt(i,null);
                    }
                }
                case "Chest" -> {
                    //((Obj_Chest)gp.getObjectAt(i)).open();
                }
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage img = null;

        switch (super.getDirection()){
            case UP -> {
                if(super.getSpriteNum()==1) img = super.getUp1();
                else if(super.getSpriteNum()==2) img = super.getUp2();
            }
            case DOWN -> {
                if(super.getSpriteNum()==1) img = super.getDown1();
                else if(super.getSpriteNum()==2) img = super.getDown2();
            }
            case LEFT -> {
                if(super.getSpriteNum()==1) img = super.getLeft1();
                else if(super.getSpriteNum()==2) img = super.getLeft2();
            }
            case RIGHT -> {
                if(super.getSpriteNum()==1) img = super.getRight1();
                else if(super.getSpriteNum()==2) img = super.getRight2();
            }
        }
        g2.drawImage(img, this.screenX, this.screenY, gp.getTileSize(), gp.getTileSize(), null);
    }


    public int getScreenX(){
        return screenX;
    }

    public int getScreenY(){
        return screenY;
    }

}
