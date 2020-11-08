package Main.utils;

import java.awt.*;

/**
 * Class for a game objects with collision , with rectangles xD
 */
public abstract class Physics {
    /**
     *Return down rectangle col
     */
    public abstract Rectangle getP();
    /**
     * Return right rectangle col
     */
    public abstract Rectangle getRightP();
    /**
     * Return left rectangle col
     */
    public abstract Rectangle getLeftP();
    /**
     * Return up rectangle col
     */
    public abstract Rectangle getToP();
}
