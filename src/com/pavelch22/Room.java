package com.pavelch22;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class.
 */
public class Room {
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;
    private static int[] levelDelay = {1000, 600, 550, 500, 480, 460, 440, 420, 400, 380, 360, 340, 320, 300, 280, 260};
    public static Room game;

    /**
     * Constructs a new room with specified width and height.
     *
     * @param width
     * @param height
     * @param snake
     */
    public Room(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
    }

    /**
     * Returns width of the room.
     *
     * @return width of the room.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width of the room.
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns height of the room.
     *
     * @return height of the room.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height of the room.
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns current snake.
     *
     * @return current snake.
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Sets a new snake.
     *
     * @param snake
     */
    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    /**
     * Returns the mouse.
     *
     * @return the mouse.
     */
    public Mouse getMouse() {
        return mouse;
    }

    /**
     * Sets a new mouse.
     *
     * @param mouse
     */
    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    /**
     * Main loop of the game.
     */
    public void run() {
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        while (snake.isAlive()) {
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                    snake.setDirection(SnakeDirection.LEFT);
                } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                    snake.setDirection(SnakeDirection.RIGHT);
                } else if (event.getKeyCode() == KeyEvent.VK_UP) {
                    snake.setDirection(SnakeDirection.UP);
                } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                    snake.setDirection(SnakeDirection.DOWN);
                }
            }
            snake.move();
            print();
            sleep();
        }
        System.out.println("Game Over");
        System.out.println("Your score: " + (snake.getSections().size() - 1) * 100);
    }

    /**
     * Prints current state of the game.
     */
    public void print() {
        int[][] matrix = new int[height][width];
        List<SnakeSection> sections = new ArrayList<>(snake.getSections());
        for (SnakeSection section : sections) {
            matrix[section.getY()][section.getX()] = 1;
        }
        matrix[snake.getY()][snake.getX()] = snake.isAlive() ? 2 : 4;
        matrix[mouse.getY()][mouse.getX()] = 3;
        String[] symbols = {" . ", " x ", " X ", "^_^", "RIP"};
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(symbols[matrix[j][i]]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    /**
     * Method is called when mouse is eaten.
     */
    public void eatMouse() {
        createMouse();
    }

    /**
     * Creates a new mouse.
     */
    public void createMouse() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);
        mouse = new Mouse(x, y);
    }

    /**
     * Pause between steps.
     */
    public void sleep() {
        try {
            int level = snake.getSections().size();
            int delay = level < 15 ? levelDelay[level] : 250;
            Thread.sleep(delay);
        } catch (InterruptedException ignore) {

        }
    }

    public static void main(String[] args) {
        game = new Room(20, 20, new Snake(10, 10));
        game.snake.setDirection(SnakeDirection.RIGHT);
        game.createMouse();
        game.run();
    }
}

