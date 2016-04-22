package com.pavelch22;

/**
 * The SnakeSection class represents the snake section.
 */
public class SnakeSection {
    private int x;
    private int y;

    /**
     * Creates a new SnakeSection with XY coordinates.
     */
    public SnakeSection(int x, int y) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnakeSection that = (SnakeSection) o;

        if (x != that.x) return false;
        return y == that.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
