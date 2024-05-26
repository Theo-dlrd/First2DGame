package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;
    private final GamePanel gp;

    public KeyHandler(GamePanel gp){
        upPressed = false;
        downPressed = false;
        leftPressed = false;
        rightPressed = false;
        ePressed = false;
        this.gp = gp;
    }

    public boolean isUpPressed(){
        return upPressed;
    }

    public boolean isDownPressed(){
        return downPressed;
    }

    public boolean isLeftPressed(){
        return leftPressed;
    }

    public boolean isRightPressed(){
        return rightPressed;
    }

    public boolean isEPressed(){
        return ePressed;
    }

    public void setEPressed(boolean b){
        ePressed = b;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //UNUSED
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(gp.getGameState()==State.TITLE){
            if(gp.getUi().getMenuState()==MenuState.MAIN) {
                if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                    gp.getUi().incrementCommandNum();
                } else if (code == KeyEvent.VK_Z || code == KeyEvent.VK_UP) {
                    gp.getUi().decrementCommandNum();
                } else if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_D) {
                    switch (gp.getUi().getCommandNum()) {
                        case 0:
                            //gp.getUi().setMenuState(MenuState.PLAYER_SELECTION);
                            gp.setGameState(State.PLAYING);
                            gp.playMusic(0);
                            break;
                        case 1:
                            //Add later
                            break;
                        case 2:
                            System.exit(0);
                            break;
                    }
                }
            }
        }
        else if(gp.getGameState()==State.PLAYING){
            if(code==KeyEvent.VK_Z){
                upPressed = true;
            }
            if(code==KeyEvent.VK_S){
                downPressed = true;
            }
            if(code==KeyEvent.VK_Q){
                leftPressed = true;
            }
            if(code==KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code==KeyEvent.VK_E){
                ePressed = true;
            }
            if(code==KeyEvent.VK_UP){
                gp.zoomIn();
            }
            if(code==KeyEvent.VK_DOWN){
                gp.zoomOut();
            }
            if(code==KeyEvent.VK_ESCAPE){
                gp.setGameState(State.PAUSE);
            }
        }
        else if(gp.getGameState()==State.PAUSE){
            if(code==KeyEvent.VK_ESCAPE){
                gp.setGameState(State.PLAYING);
            }
        }
        else if(gp.getGameState()==State.DIALOGUE){
            if(code==KeyEvent.VK_E){
                if(gp.getPlayer().getTalkingTo().getDialogueAt(gp.getPlayer().getTalkingTo().getDialogIndex())==null){
                    System.out.println("fin dialogue");
                    gp.getPlayer().getTalkingTo().setDialogIndex(0);
                    gp.getUi().setCurrentDialogue("");
                    gp.getPlayer().setTalkingTo(null);
                    gp.setGameState(State.PLAYING);
                }
                if(gp.getPlayer().getTalkingTo()!=null) gp.getUi().nextDialogue();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code==KeyEvent.VK_Z){
            upPressed = false;
        }
        if(code==KeyEvent.VK_S){
            downPressed = false;
        }
        if(code==KeyEvent.VK_Q){
            leftPressed = false;
        }
        if(code==KeyEvent.VK_D){
            rightPressed = false;
        }
    }

}
