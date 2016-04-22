package com.pavelch22;

/**
 * The Mouse class represents a mouse that have to be eaten by a snake.
 */
public class Mouse {
    private int x;
    private int y;

    /**
     * Creates a new mouse with XY coordinates.
     */
    public Mouse(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns X coordinate.
     *
     * @return X coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns Y coordinate.
     *
     * @return Y coordinate.
     */
    public int getY() {
        return y;
    }
}
