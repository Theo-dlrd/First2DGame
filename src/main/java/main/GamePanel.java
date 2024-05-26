package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import object.SuperObject;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    private final int originalTileSize = 16; //16x16 tile

    //Scale
    private final int scale  = 3;
    private int tileSize = originalTileSize * scale; // 48x48 tile
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 12;
    private final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    private final int screenHeight = tileSize * maxScreenRow;   // 576 pixels

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
    private final Entity[] npcs;
    private final AssetSetter assetSetter;
    private final Sound music;
    private final Sound soundEffect;
    private final UI ui;

    //GAME STATE
    private State gameState;

    //FPS
    private final double FPS = 60.0;

    public GamePanel(){
        keyH = new KeyHandler(this);
        player = new Player(this,keyH);
        tileManager = new TileManager(this);
        collisionChecker = new CollisionChecker(this);
        objects = new SuperObject[10];
        assetSetter = new AssetSetter(this);
        music = new Sound();
        soundEffect = new Sound();
        ui = new UI(this);
        npcs = new Entity[10];

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        setupGame();
    }

    public void setupGame(){
        assetSetter.setObject();
        assetSetter.setNPC();
        gameState = State.TITLE;
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
        if(gameState==State.PLAYING){
            player.update();
            for (int i = 0; i < npcs.length; i++) {
                if(npcs[i]!=null) npcs[i].update();
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        if(gameState == State.TITLE){
            ui.draw(g2);
        }
        else {
            tileManager.draw(g2);
            for (int i = 0; i < objects.length; i++) {
                if (getObjectAt(i) != null) getObjectAt(i).draw(g2, this);
            }
            for (int i = 0; i < npcs.length; i++) {
                if (getNPCAt(i) != null) getNPCAt(i).draw(g2);
            }
            player.draw(g2);
            ui.draw(g2);

            g2.dispose();
        }
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

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        soundEffect.setFile(i);
        soundEffect.play();
    }

    public UI getUi(){
        return ui;
    }

    public void setGameThread(Thread th){
        this.gameThread = th;
    }

    public State getGameState(){
        return gameState;
    }

    public void setGameState(State st){
        this.gameState = st;
    }

    public void setNPCAt(int i, Entity e){
        this.npcs[i] = e;
    }

    public Entity getNPCAt(int i){
        return this.npcs[i];
    }

    public Entity[] getNPCArray(){
        return this.npcs;
    }

    public KeyHandler getKeyHandler(){
        return this.keyH;
    }
}
