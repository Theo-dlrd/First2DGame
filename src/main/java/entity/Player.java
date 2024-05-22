package entity;

import main.GamePanel;
import main.KeyHandler;
import main.State;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    private final KeyHandler keyH;
    private final int screenX;
    private final int screenY;
    //private int hasKey;
    private int standCounter;
    private Entity talkingTo;


    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;
        //this.hasKey = 0;
        this.standCounter = 0;
        this.talkingTo = null;

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
        setUp1Image(setup("boy_up_1"));
        setUp2Image(setup("boy_up_2"));
        setDown1Image(setup("boy_down_1"));
        setDown2Image(setup("boy_down_2"));
        setLeft1Image(setup("boy_left_1"));
        setLeft2Image(setup("boy_left_2"));
        setRight1Image(setup("boy_right_1"));
        setRight2Image(setup("boy_right_2"));
    }

    private BufferedImage setup(String imgName){
        UtilityTool UT = new UtilityTool();
        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/Player/Walking/"+imgName+".png"));
            scaledImage = UT.scaledImage(scaledImage,gp.getTileSize(), gp.getTileSize());
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return scaledImage;
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

            int npcIndex = gp.getCollisionChecker().checkEntity(this, gp.getNPCArray());
            interactNPC(npcIndex);

            //int objIndex = gp.getCollisionChecker().checkObject(this,true);
            //pickUpObject(objIndex);

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
        else{
            standCounter++;
            if(standCounter == 20){
                setSpriteNum(1);
                standCounter=0;
            }
        }
    }

    /*
    public void pickUpObject(int i){
        if(i>=0){
            String objName = gp.getObjectAt(i).getName();
            switch (objName){
                case "Key" -> {
                    gp.playSE(1);
                    hasKey++;
                    gp.setObjectAt(i,null);
                    gp.getUi().showMessage("You got a key !");
                }
                case "Door" -> {
                    if(hasKey>0){
                        gp.playSE(3);
                        hasKey--;
                        gp.setObjectAt(i,null);
                        gp.getUi().showMessage("You opened the door !");
                    }
                    else{
                        gp.getUi().showMessage("You need a key to open this door !");
                    }
                }
                case "Chest" -> {
                    if(!((Obj_Chest)gp.getObjectAt(i)).isOpen()) {
                        gp.getUi().finishGame();
                        gp.stopMusic();
                        gp.playSE(4);
                        ((Obj_Chest) gp.getObjectAt(i)).open();
                    }
                }
                case "Boots" -> {
                    gp.playSE(2);
                    incrementSpeed(1);
                    gp.setObjectAt(i, null);
                    gp.getUi().showMessage("Speed Up !");
                }
            }
        }
    }
    */

    public void pickUpObject(int i){
        if(i>=0){

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
        g2.drawImage(img, this.screenX, this.screenY, null);

        //g2.setColor(Color.red);
        //g2.drawRect(screenX+getSolidArea().x, screenY+getSolidArea().y, getSolidArea().width, getSolidArea().height);
    }


    public int getScreenX(){
        return screenX;
    }

    public int getScreenY(){
        return screenY;
    }

    /*
    public int getNbKey(){
        return hasKey;
    }
    */

    public void interactNPC(int npcIndex){
        if(npcIndex>=0 && gp.getKeyHandler().isEPressed()){
            this.talkingTo = gp.getNPCAt(npcIndex);
            gp.setGameState(State.DIALOGUE);
        }
        gp.getKeyHandler().setEPressed(false);
    }

    public Entity getTalkingTo(){
        return talkingTo;
    }

    public void setTalkingTo(Entity entity){
        this.talkingTo = entity;
    }

}
