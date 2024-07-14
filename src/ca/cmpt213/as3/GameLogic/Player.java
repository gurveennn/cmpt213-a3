package ca.cmpt213.as3.GameLogic;

import ca.cmpt213.as3.UI.GameBoard;

/**
 * in this class:
 * Gives player position on the game board
 * Move around the game board using W, A, S, D keys
 * Handles invalid inputs for movements
 * Keeping track of collected Tokimons
 */

public class Player {
    private int positionX;
    private int positionY;
    private int TokimonScore = 0;
    private int spells = 3;

    public void collectTokimon() {
        TokimonScore++;
    }

    public int getTokimonScore(){
        return TokimonScore;
    }

    public int getSpells() {
        return spells;
    }

    public void setSpells(int spells) {
        this.spells = spells;
    }

    public void setPosition(String initialPos) throws IllegalArgumentException {
        initialPos = initialPos.toUpperCase();

        if(initialPos.length() < 2 || initialPos.length() > 3) {
            throw new IllegalArgumentException("Invalid position: <letter><number> ex: B5");
        }

        if(initialPos.charAt(0) < 'A' || initialPos.charAt(0) > 'J') {
            throw new IllegalArgumentException("Invalid position, must be a letter from A to J: <letter><number> ex: B6");
        }

        if(Integer.parseInt(initialPos.substring(1)) < 1 || Integer.parseInt(initialPos.substring(1)) > 10) {
            throw new IllegalArgumentException("Invalid position, must be a number from 1 to 10: <letter><number> ex: B7");
        }

        this.positionY = initialPos.charAt(0) - 'A';
        this.positionX = Integer.parseInt(initialPos.substring(1)) - 1;
    }

    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }

    public void move(GameBoard gameBoard, String direction) {
        switch (direction) {
            case "w":
                if (positionY > 0) {
                    positionY--;
                }
            break;
            case "a":
                if (positionX > 0) {
                    positionX--;
                }
                break;
            case "s":
                if (positionY < gameBoard.getRows() - 1) {
                    positionY++;
                }
                break;
            case "d":
                if (positionX < gameBoard.getCols() - 1) {
                    positionX++;
                }
                break;
            //ignore x
            case "x":
                break;
            default:
                System.out.println("Invalid direction: use w, a, s, d to move");
        }
    }
}
