package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    private double worldX, worldY;
    private double speed;
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private Direction direction;
    private int spriteCounter;
    private int spriteNum;
    private final Rectangle solidArea;
    private double solidAreaDefaultX, solidAreaDefaultY;
    private boolean collisionOn;
    protected GamePanel gp;


    protected Entity(GamePanel gp){
        this.gp = gp;
        this.collisionOn = false;
        this.spriteCounter = 0;
        this.spriteNum = 1;
        this.solidArea = new Rectangle(gp.getTileSize()/4, gp.getTileSize()/2, gp.getTileSize()/2, gp.getTileSize()/2);
        solidAreaDefaultX = solidArea.getX();
        solidAreaDefaultY = solidArea.getY();
    }

    public void razSprinteCounter(){
        spriteCounter = 0;
    }

    public void incrementSpriteCounter(){
        spriteCounter++;
    }

    public int getSpriteCounter(){
        return spriteCounter;
    }

    public int getSpriteNum(){
        return spriteNum;
    }

    public void incrementSpriteNum(){
        spriteNum++;
    }

    public void decrementSpriteNum(){
        spriteNum--;
    }


    public double getSolidAreaDefaultX(){
        return solidAreaDefaultX;
    }

    public double getSolidAreaDefaultY(){
        return solidAreaDefaultY;
    }

    public double getX(){
        return worldX;
    }

    public double getY(){
        return worldY;
    }

    public double getSpeed(){
        return speed;
    }


    public void setX(double x){
        this.worldX = x;
    }

    public void setY(double y){
        this.worldY = y;
    }

    public void setSpeed(double sp){
        this.speed = sp;
    }

    public void incrementX(double val){
        this.setX(this.getX() + val);
    }

    public void decrementX(double val){
        this.setX(this.getX() - val);
    }

    public void incrementY(double val){
        this.setY(this.getY() + val);
    }

    public void decrementY(double val){
        this.setY(this.getY() - val);
    }

    public void incrementSpeed(int val){
        this.setSpeed(this.getSpeed() + val);
    }

    public void decrementSpeed(int val){
        this.setSpeed(this.getSpeed() - val);
    }

    protected void setUp1Image(BufferedImage img){
        this.up1 = img;
    }
    protected void setUp2Image(BufferedImage img){
        this.up2 = img;
    }
    protected void setDown1Image(BufferedImage img){
        this.down1 = img;
    }
    protected void setDown2Image(BufferedImage img){
        this.down2 = img;
    }
    protected void setLeft1Image(BufferedImage img){
        this.left1 = img;
    }
    protected void setLeft2Image(BufferedImage img){
        this.left2 = img;
    }
    protected void setRight1Image(BufferedImage img){
        this.right1 = img;
    }
    protected void setRight2Image(BufferedImage img){
        this.right2 = img;
    }

    protected BufferedImage getUp1(){
        return up1;
    }

    protected BufferedImage getUp2(){
        return up2;
    }

    protected BufferedImage getDown1(){
        return down1;
    }

    protected BufferedImage getDown2(){
        return down2;
    }

    protected BufferedImage getLeft1(){
        return left1;
    }

    protected BufferedImage getLeft2(){
        return left2;
    }

    protected BufferedImage getRight1(){
        return right1;
    }

    protected BufferedImage getRight2(){
        return right2;
    }

    protected void setDirection(Direction dir){
        this.direction = dir;
    }
    public Direction getDirection(){
        return direction;
    }

    protected boolean getCollision(){
        return collisionOn;
    }

    public void setCollisionOn(boolean bool){
        this.collisionOn = bool;
    }

    public Rectangle getSolidArea(){
        return solidArea;
    }

}
