package main;

import entity.Player;
import tile.TileManager;
import Object.SuperObject;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    private final int originalTileSize = 16; //16x16 tile

    //Scale
    private final int scale  = 3;
    private int tileSize = originalTileSize * scale; // 48x48 tile
    private int maxScreenCol = 16;
    private int maxScreenRow = 12;
    private int screenWidth = tileSize * maxScreenCol; // 768 pixels
    private int screenHeight = tileSize * maxScreenRow;   // 576 pixels

    // WORLD SETTINGS
    private final int maxWorldCol=50;
    private final int maxWorldRow=50;
    private final int worldWidth = tileSize * maxWorldCol;
    private final int worldHeight = tileSize * maxWorldRow;


    private final KeyHandler keyH;
    private Thread gameThread;
    private final Player player;
    private final TileManager tileManager;
    private final CollisionChecker collisionChecker;
    private final SuperObject[] objects;
    private final AssetSetter assetSetter;


    //FPS
    private final double FPS = 60.0;

    public GamePanel(){
        keyH = new KeyHandler(this);
        player = new Player(this,keyH);
        tileManager = new TileManager(this);
        collisionChecker = new CollisionChecker(this);
        objects = new SuperObject[10];
        assetSetter = new AssetSetter(this);

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        assetSetter.setObject();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread!=null){
            currentTime = System.nanoTime();

            if(currentTime-lastTime >= drawInterval) {
                //1. UPDATE: Update info such as character position
                update();
                //2. DRAW: draw the screen
                repaint();

                lastTime = currentTime;
            }
        }
    }


    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        for (int i = 0; i < objects.length; i++) {
            if(getObjectAt(i)!=null) getObjectAt(i).draw(g2,this);
        }
        player.draw(g2);

        g2.dispose();
    }

    public int getTileSize(){
        return tileSize;
    }

    public int getMaxScreenCol(){
        return this.maxScreenCol;
    }

    public int getMaxScreenRow(){
        return this.maxScreenRow;
    }

    public int getScreenWidth(){
        return this.screenWidth;
    }

    public int getScreenHeight(){
        return this.screenHeight;
    }

    public int getWorldWidth(){
        return worldWidth;
    }

    public int getWorldHeight(){
        return worldHeight;
    }

    public int getMaxWorldCol(){
        return maxWorldCol;
    }

    public int getMaxWorldRow(){
        return maxWorldRow;
    }

    public Player getPlayer(){
        return player;
    }

    public void zoomIn(){
        int oldWorldWidth = tileSize * maxWorldCol;
        tileSize += 1;
        int newWorldWidth = tileSize * maxWorldCol;

        player.setSpeed(newWorldWidth/600.0);

        double multiplier = (double) newWorldWidth/oldWorldWidth;

        double newPlayerWorldX = player.getX() * multiplier;
        double newPlayerWorldY = player.getY() * multiplier;

        player.setX(newPlayerWorldX);
        player.setY(newPlayerWorldY);
    }

    public void zoomOut(){
        int oldWorldWidth = tileSize * maxWorldCol;
        tileSize -= 1;
        int newWorldWidth = tileSize * maxWorldCol;

        player.setSpeed(newWorldWidth/600.0);

        double multiplier = (double) newWorldWidth/oldWorldWidth;

        double newPlayerWorldX = player.getX() * multiplier;
        double newPlayerWorldY = player.getY() * multiplier;

        player.setX(newPlayerWorldX);
        player.setY(newPlayerWorldY);
    }

    public CollisionChecker getCollisionChecker(){
        return collisionChecker;
    }

    public TileManager getTileManager(){
        return tileManager;
    }

    public SuperObject getObjectAt(int i){
        return objects[i];
    }

    public void setObjectAt(int i, SuperObject obj){
        objects[i] = obj;
    }

    public int getNbObjects(){
        return objects.length;
    }

}
