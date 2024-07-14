package ca.cmpt213.as3.UI;

/**
 * this class displays full game board for cheat mode incldugn where all the Fokimon are before
 * the game begins
 */

public class CheatMode {

    public static void displayCheatBoard(GameBoard gameBoard) {
        System.out.println("Cheat mode:");
        System.out.print(" ");

        for (int j = 1; j <= gameBoard.getCols(); j++) {
            System.out.printf("%7d", j);
        }
        System.out.println();
        for (int i = 0; i < gameBoard.getRows(); i++) {
            // print cols
            System.out.print((char) ('A' + i));
            for (int j = 0; j < gameBoard.getCols(); j++) {
                System.out.print(" ");
                // foki pos
                if(gameBoard.getFokiPos(i,j)){
                    System.out.printf("%6s", "X");
                } else {
                    // unexploerd
                    System.out.printf("%6s", gameBoard.getBoardPosition(i,j));
                }
            }
            System.out.println();
        }
    }
}
