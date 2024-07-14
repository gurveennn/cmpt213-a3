package ca.cmpt213.as3.UI;

import ca.cmpt213.as3.GameLogic.Player;

import java.util.Random;

/**
 * in this class:
 * Creates game board with specified preconditions
 * Put Tokimons and Fokimons randomly on the board
 * Displays board to player
 * Updates the board according to the player’s movement
 * Keep track of win/lose
 */

public class GameBoard {
    private final char[][] board;
    private final boolean[][] tokiPos;
    private final boolean[][] fokiPos;
    private final int rows = 10;
    private final int cols = 10;
    private final int numTokimon;
    private final int numFokmon;
    private boolean gameOver = false;

    public GameBoard(int numTokimon, int numFokmon) {
        this.numTokimon = numTokimon;
        this.numFokmon = numFokmon;
        board = new char[rows][cols];
        tokiPos = new boolean[rows][cols];
        fokiPos = new boolean[rows][cols];
        //make board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '~';
                tokiPos[i][j] = false;
                fokiPos[i][j] = false;
            }
        }
    }

    public void placeTokiAndFoki() {
        Random rand = new Random();
        // Place Tokimons
        for (int i = 0; i < numTokimon; i++) {
            int x, y;
            do {
                x = rand.nextInt(rows);
                y = rand.nextInt(cols);
            } while (tokiPos[x][y] || fokiPos[x][y]);
            tokiPos[x][y] = true;
        }
        // Place Fokimons
        for (int i = 0; i < numFokmon; i++) {
            int x, y;
            do {
                x = rand.nextInt(rows);
                y = rand.nextInt(cols);
            } while (tokiPos[x][y] || fokiPos[x][y]);
            fokiPos[x][y] = true;
        }
    }

    public void displayBoard(Player player) {
        System.out.print(" ");
        // print rows
        for (int j = 1; j <= cols; j++) {
            System.out.printf("%7d", j);
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            // print cols
            System.out.print((char) ('A' + i));
            for (int j = 0; j < cols; j++) {
                System.out.print(" ");
                if (player.getPositionY() == i && player.getPositionX() == j) {
                    // player position
                    if (tokiPos[i][j]) {
                        System.out.printf("%6s", "@$");
                    } else {
                        System.out.printf("%6s", "@");
                    }
                }
                else if (board[i][j] == '$') {
                    // found Tokimon
                    System.out.printf("%6s", "$");
                } else if (board[i][j] == ' ') {
                    // already explored
                    System.out.printf("%6s", " ");
                } else {
                    // unexploerd
                    System.out.printf("%6s", board[i][j]);
                }
            }
            System.out.println();
        }
    }

    //update the board
    public void updateBoard(Player player) {
        int x = player.getPositionY();
        int y = player.getPositionX();
        if(tokiPos[x][y]) {
            player.collectTokimon();
            board[x][y] = '$';
            tokiPos[x][y] = false; // already found now
            System.out.println("Yay! You found a Tokimon ;)");

        } else if(fokiPos[x][y]) {
            board[x][y] = 'X';
            System.out.println("You landed on Fokimon! Game Over :(");
            displayFullBoard(player);
            gameOver = true;
        }
        else {
            board[x][y] = ' ';
        }
    }

    public void displayFullBoard(Player player) {
        System.out.print(" ");
        // Print column headers
        for (int j = 1; j <= cols; j++) {
            System.out.printf("%7d", j);
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            // Print row headers
            System.out.print((char) ('A' + i));
            for (int j = 0; j < cols; j++) {
                System.out.print(" ");
                if (player.getPositionY() == i && player.getPositionX() == j) {
                    // Player position
                    System.out.printf("%6s", "@" + board[i][j]);
                } else if (board[i][j] == '$' || tokiPos[i][j]) {
                    // Found Tokimon
                    System.out.printf("%6s", "$");
                } else if (board[i][j] == 'X' || fokiPos[i][j]) {
                    // Found Fokimon
                    System.out.printf("%6s", "X");
                } else if (board[i][j] == ' ' || board[i][j] == '~') {
                    // Already explored
                    System.out.printf("%6s", " ");
                } else {
                    // Default case
                    System.out.printf("%6s", board[i][j]);
                }
            }
            System.out.println();
        }
    }

    public void revealTokimon() {
        boolean tokimonFound = false;
        for (int i = 0; i < rows && !tokimonFound; i++) {
            for (int j = 0; j < cols && !tokimonFound; j++) {
                if(tokiPos[i][j]) {
                    board[i][j] = '$';
                    tokimonFound = true;
                }
            }
        }
    }

    public void removeFokimon(GameBoard gameBoard) {
        boolean fokimonFound = false;
        for (int i = 0; i < rows && !fokimonFound; i++) {
            for (int j = 0; j < cols && !fokimonFound; j++) {
                if(fokiPos[i][j]) {
                    board[i][j] = ' ';
                    fokiPos[i][j] = false;
                    fokimonFound = true;
                }
            }
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getBoardPosition(int x, int y) {
        return board[x][y];
    }

    public int getRows(){
        return rows;
    }

    public int getCols(){
        return cols;
    }

    public boolean getFokiPos(int x, int y){
        return fokiPos[x][y];
    }
}

