package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Chest extends SuperObject{

    private boolean isOpened;

    public Obj_Chest(){
        super("Chest");
        this.isOpened = false;
        try{
            setImage(ImageIO.read(getClass().getResourceAsStream("/Objects/chest.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        setCollision(true);
    }

    public void open(){
        if(!isOpened) isOpened = true;
        try{
            setImage(ImageIO.read(getClass().getResourceAsStream("/Objects/chest_opened.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
