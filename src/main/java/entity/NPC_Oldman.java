package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class NPC_Oldman extends Entity{

    public NPC_Oldman(GamePanel gp){
        super(gp);
        setDirection(Direction.DOWN);
        setSpeed(1);
        getNPC_OldmanImages();
        setDialogues();
    }

    public void getNPC_OldmanImages(){
        setUp1Image(setup("oldman_up_1"));
        setUp2Image(setup("oldman_up_2"));
        setDown1Image(setup("oldman_down_1"));
        setDown2Image(setup("oldman_down_2"));
        setLeft1Image(setup("oldman_left_1"));
        setLeft2Image(setup("oldman_left_2"));
        setRight1Image(setup("oldman_right_1"));
        setRight2Image(setup("oldman_right_2"));
    }

    private BufferedImage setup(String imgName){
        UtilityTool UT = new UtilityTool();
        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/NPC/"+imgName+".png"));
            scaledImage = UT.scaledImage(scaledImage,gp.getTileSize(), gp.getTileSize());
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return scaledImage;
    }

    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter==120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                setDirection(Direction.UP);
            } else if (i <= 50) {
                setDirection(Direction.DOWN);
            } else if (i <= 75) {
                setDirection(Direction.LEFT);
            } else {
                setDirection(Direction.RIGHT);
            }
            actionLockCounter=0;
        }
    }

    public void setDialogues(){
        dialogues[0] = "Hello, little boy !";
        dialogues[1] = "Did you see how the sky is beautiful and \nhow the birds flight in that blue and \novercast sky ?";
        dialogues[2] = "Ho you're not here for the sky...\nYou need a new quest, right ?";
        dialogues[3] = "Ok, so...";
        dialogues[4] = "GO FUCK YOURSELF !!!!!!";
    }

    public void speak(){
        super.speak();
    }
}
