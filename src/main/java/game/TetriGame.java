package game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TetriGame {
    private static final int ROWS = 20;
    private static final int COLS = 10;
    private static final int SHAPE_SIZE = 4;

    private static final char EMPTY = '.';
    private static final char BLOCK = '#';

    private char[][] board;
    private char[][] currentShape;
    private int currentShapeRow;
    private int currentShapeCol;

    public TetriGame() {
        board = new char[ROWS][COLS];
        currentShape = new char[SHAPE_SIZE][SHAPE_SIZE];
        currentShapeRow = 0;
        currentShapeCol = COLS / 2 - SHAPE_SIZE / 2;

        // Initialize the board with empty cells
        for (char[] row : board) {
            Arrays.fill(row, EMPTY);
        }

        // Generate a random shape for the initial piece
        generateRandomShape();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayBoard();

            System.out.println("Enter a command (a: move left, d: move right, s: move down, q: quit): ");
            String command = scanner.nextLine();

            switch (command) {
                case "a":
                    moveLeft();
                    break;
                case "d":
                    moveRight();
                    break;
                case "s":
                    moveDown();
                    break;
                case "q":
                    System.out.println("Game over!");
                    return;
                default:
                    System.out.println("Invalid command!");
            }

            if (!canMoveDown()) {
                placeShapeOnBoard();
                clearLines();
                generateRandomShape();

                if (!canMoveDown()) {
                    System.out.println("Game over!");
                    return;
                }
            }
        }
    }

    private void displayBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void generateRandomShape() {
        // Generate a random shape (2D array) for the current piece
        Random random = new Random();
        int shapeIndex = random.nextInt(7);

        switch (shapeIndex) {
            case 0:
                currentShape = new char[][]{{BLOCK, BLOCK, BLOCK, BLOCK}};
                break;
            case 1:
                currentShape = new char[][]{{BLOCK, BLOCK}, {BLOCK, BLOCK}};
                break;
            case 2:
                currentShape = new char[][]{{BLOCK, BLOCK, BLOCK}, {EMPTY, EMPTY, BLOCK}};
                break;
            case 3:
                currentShape = new char[][]{{BLOCK, BLOCK, BLOCK}, {BLOCK, EMPTY, EMPTY}};
                break;
            case 4:
                currentShape = new char[][]{{BLOCK, BLOCK, BLOCK}, {EMPTY, BLOCK, EMPTY}};
                break;
            case 5:
                currentShape = new char[][]{{BLOCK, BLOCK, EMPTY}, {EMPTY, BLOCK, BLOCK}};
                break;
            case 6:
                currentShape = new char[][]{{EMPTY, BLOCK, BLOCK}, {BLOCK, BLOCK, EMPTY}};
                break;
        }

        currentShapeRow = 0;
        currentShapeCol = COLS / 2 - SHAPE_SIZE / 2;
    }

    private void moveLeft() {
        if (canMoveLeft()) {
            currentShapeCol--;
        }
    }

    private boolean canMoveLeft() {
        for (int row = 0; row < SHAPE_SIZE; row++) {
            for (int col = 0; col < SHAPE_SIZE; col++) {
                if (currentShape[row][col] == BLOCK) {
                    int boardRow = currentShapeRow + row;
                    int boardCol = currentShapeCol + col - 1;
                    if (boardCol < 0 || board[boardRow][boardCol] == BLOCK) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void moveRight() {
        if (canMoveRight()) {
            currentShapeCol++;
        }
    }

    private boolean canMoveRight() {
        for (int row = 0; row < SHAPE_SIZE; row++) {
            for (int col = 0; col < SHAPE_SIZE; col++) {
                if (currentShape[row][col] == BLOCK) {
                    int boardRow = currentShapeRow + row;
                    int boardCol = currentShapeCol + col + 1;
                    if (boardCol >= COLS || board[boardRow][boardCol] == BLOCK) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void moveDown() {
        if (canMoveDown()) {
            currentShapeRow++;
        }
    }

    private boolean canMoveDown() {
        for (int row = 0; row < SHAPE_SIZE; row++) {
            for (int col = 0; col < SHAPE_SIZE; col++) {
                if (currentShape[row][col] == BLOCK) {
                    int boardRow = currentShapeRow + row + 1;
                    int boardCol = currentShapeCol + col;
                    if (boardRow >= ROWS || board[boardRow][boardCol] == BLOCK) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void placeShapeOnBoard() {
        for (int row = 0; row < SHAPE_SIZE; row++) {
            for (int col = 0; col < SHAPE_SIZE; col++) {
                if (currentShape[row][col] == BLOCK) {
                    int boardRow = currentShapeRow + row;
                    int boardCol = currentShapeCol + col;
                    board[boardRow][boardCol] = BLOCK;
                }
            }
        }
    }

    private void clearLines() {
        for (int row = ROWS - 1; row >= 0; row--) {
            boolean isLineFull = true;
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == EMPTY) {
                    isLineFull = false;
                    break;
                }
            }
            if (isLineFull) {
                // Move all lines above down by one row
                for (int r = row; r > 0; r--) {
                    System.arraycopy(board[r - 1], 0, board[r], 0, COLS);
                }
                // Clear the top row
                Arrays.fill(board[0], EMPTY);
            }
        }
    }

    public static void main(String[] args) {
        TetriGame game = new TetriGame();
        game.play();
    }
}

