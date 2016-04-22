package com.pavelch22;

import java.util.ArrayList;
import java.util.List;

/**
 * The Snake class represents a snake that consists of SnakeSections.
 */
public class Snake {
    private SnakeDirection direction;
    private boolean isAlive;
    private List<SnakeSection> sections;

    /**
     * Constructs a new snake in the specified coordinates.
     */
    public Snake(int x, int y) {
        sections = new ArrayList<>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    /**
     * Returns true if the snake is alive.
     *
     * @return returns true if the snake is alive.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Returns X coordinate of the snake's head.
     *
     * @return returns X coordinate of the snake's head.
     */
    public int getX() {
        return sections.get(0).getX();
    }

    /**
     * Returns Y coordinate of the snake's head.
     *
     * @return returns Y coordinate of the snake's head.
     */
    public int getY() {
        return sections.get(0).getY();
    }

    /**
     * Returns the direction of the snake's head.
     *
     * @return direction of the snake's head.
     */
    public SnakeDirection getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the snake's head.
     *
     * @param direction
     */
    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    /**
     * Returns List of all snake's segments.
     *
     * @return returns List of all snake's segments.
     */
    public List<SnakeSection> getSections() {
        return sections;
    }

    /**
     * This method makes one snake's step. Direction of movement is
     * declared in {@link #direction} variable.
     */
    public void move() {
        if (!isAlive) {
            return;
        }
        if (direction == SnakeDirection.LEFT) {
            move(-1, 0);
        } else if (direction == SnakeDirection.RIGHT) {
            move(1, 0);
        } else if (direction == SnakeDirection.UP) {
            move(0, -1);
        } else if (direction == SnakeDirection.DOWN) {
            move(0, 1);
        }
    }

    /**
     * This method moves the snake in the next cell. Snake moves by
     * adding an offset (dx, dy) to the current coordinates of the head and
     * deleting the last segment if the mouse isn't eaten.
     *
     * @param dx X offset
     * @param dy Y offset
     */
    private void move(int dx, int dy) {
        SnakeSection head = sections.get(0);
        head = new SnakeSection(head.getX() + dx, head.getY() + dy);
        checkBorders(head);
        if (!isAlive) {
            return;
        }
        checkBody(head);
        if (!isAlive) {
            return;
        }
        Mouse mouse = Room.game.getMouse();
        if (head.getX() == mouse.getX() && head.getY() == mouse.getY()) {//eat
            sections.add(0, head);
            Room.game.eatMouse();
        } else {//just move
            sections.add(0, head);
            sections.remove(sections.size() - 1);
        }
    }

    /**
     * This method checks whether the snake came out of the room.
     *
     * @param head current head.
     */
    private void checkBorders(SnakeSection head) {
        if (head.getX() < 0 || head.getX() >= Room.game.getWidth() || head.getY() < 0 || head.getY() >= Room.game.getHeight()) {
            isAlive = false;
        }
    }

    /**
     * This method checks whether the head crosses with any segment of the snake.
     *
     * @param head current head.
     */
    private void checkBody(SnakeSection head) {
        if (sections.contains(head)) {
            isAlive = false;
        }
    }

}
