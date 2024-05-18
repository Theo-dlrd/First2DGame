package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Door extends SuperObject{

    public Obj_Door(GamePanel gp){
        super("Door", gp);
        try{
            setImage(ImageIO.read(getClass().getResourceAsStream("/Objects/door.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        setCollision(true);
    }

}
