package main;

import object.Obj_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

public class UI {
    private final GamePanel gp;
    private final Font textFont, arial_80B;
    private final BufferedImage keyImage;
    private boolean messageOn;
    private String message;
    private int messageCounter;
    private boolean gameFinished;
    private double playTime;
    private DecimalFormat dFormat;

    public UI(GamePanel gp){
        this.gp = gp;
        textFont = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        Obj_Key objKey = new Obj_Key(gp);
        keyImage = objKey.getImage();
        messageOn = false;
        message = "";
        messageCounter = 0;
        gameFinished = false;
        dFormat = new DecimalFormat("#0.00");
    }

    public void draw(Graphics2D g2) {
        if(gameFinished){
            g2.setFont(textFont);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure !";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x=gp.getScreenWidth()/2-textLength/2;
            y=gp.getScreenHeight()*2/3;
            g2.drawString(text,x,y);

            text = "Your time is : " + dFormat.format(playTime) + " !";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x=gp.getScreenWidth()/2-textLength/2;
            y=gp.getScreenHeight()*2/3 + gp.getTileSize()*2;
            g2.drawString(text,x,y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text = "Congratulations !";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x=gp.getScreenWidth()/2-textLength/2;
            y=gp.getScreenHeight()/3;
            g2.drawString(text,x,y);

            gp.setGameThread(null);
        }
        else{
            g2.setFont(textFont);
            g2.setColor(Color.WHITE);
            g2.drawImage(keyImage, gp.getTileSize() / 2, gp.getTileSize() / 2, gp.getTileSize(), gp.getTileSize(), null);
            g2.drawString("x " + gp.getPlayer().getNbKey(), 74, 65);

            playTime += (double) 1/60;
            g2.drawString("Time: "+dFormat.format(playTime), gp.getTileSize()*11, 65);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(message, gp.getTileSize() / 2, gp.getTileSize() * 2);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }

    public void showMessage(String txt){
        message = txt;
        messageOn = true;
    }

    public String getMessage(){
        return message;
    }

    public void finishGame(){
        gameFinished = true;
    }
}