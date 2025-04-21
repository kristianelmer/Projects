package tycoon.controller;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles keyboard input for the game.
 * Implements KeyListener to capture keyboard events and track the state of game controls.
 */
public class Controller implements KeyListener{
    /** Flag for up movement key being pressed (W or Up Arrow) */
    public boolean upPressed;
    /** Flag for down movement key being pressed (S or Down Arrow) */
    public boolean downPressed;
    /** Flag for left movement key being pressed (A or Left Arrow) */
    public boolean leftPressed;
    /** Flag for right movement key being pressed (D or Right Arrow) */
    public boolean rightPressed;
    /** Flag for escape key being pressed */
    public boolean escapePressed;
    /** Flag for space key being pressed */
    public boolean spacepressed;
    /** Flag for Q key being pressed */
    public boolean qpressed;
    /** Flag for R key being pressed */
    public boolean rpressed;
    /** Flag for K key being pressed */
    public boolean kpressed;

    /**
     * Not used in this implementation.
     * 
     * @param e The key event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Handles key press events and updates the corresponding control flags.
     * 
     * @param e The key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            leftPressed = true;
            break;
            case KeyEvent.VK_RIGHT:
            rightPressed = true;
            break;
            case KeyEvent.VK_DOWN:
            downPressed = true;
            break;
            case KeyEvent.VK_UP:
            upPressed = true;
            break;
            case KeyEvent.VK_A:
            leftPressed = true;
            break;
            case KeyEvent.VK_D:
            rightPressed = true;
            break;
            case KeyEvent.VK_S:
            downPressed = true;
            break;
            case KeyEvent.VK_W:
            upPressed = true;
            break;
            case KeyEvent.VK_ESCAPE:
            escapePressed = true;
            break;
            case KeyEvent.VK_SPACE:
            spacepressed = true;
            break;
            case KeyEvent.VK_Q:
            qpressed = true;
            break;
            case KeyEvent.VK_R:
            rpressed = true;
            break;
            case KeyEvent.VK_K:
            kpressed = true;
            break;
        }
    }

    /**
     * Handles key release events and updates the corresponding control flags.
     * 
     * @param e The key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            leftPressed = false;
            break;
            case KeyEvent.VK_RIGHT:
            rightPressed = false;
            break;
            case KeyEvent.VK_DOWN:
            downPressed = false;
            break;
            case KeyEvent.VK_UP:
            upPressed = false;
            break;
            case KeyEvent.VK_A:
            leftPressed = false;
            break;
            case KeyEvent.VK_D:
            rightPressed = false;
            break;
            case KeyEvent.VK_S:
            downPressed = false;
            break;
            case KeyEvent.VK_W:
            upPressed = false;
            break;
            case KeyEvent.VK_ESCAPE:
            escapePressed = false;
            break;
            case KeyEvent.VK_SPACE:
            spacepressed = false;
            break;
            case KeyEvent.VK_R:
            rpressed = false;
            break;
            case KeyEvent.VK_K:
            kpressed = false;
            break;
        }
    }
}
