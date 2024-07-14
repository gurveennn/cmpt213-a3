package ca.cmpt213.as3;

import ca.cmpt213.as3.GameLogic.GameSetup;
import ca.cmpt213.as3.GameLogic.Player;
import ca.cmpt213.as3.GameLogic.Spell;
import ca.cmpt213.as3.UI.CheatMode;
import ca.cmpt213.as3.UI.GameBoard;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * this class:
 * Has the main function which takes in player arguments from command line
 * Sets up game and game loop
 * Keeps track of score (how many Tokimons are found)
 * Keeps track if player wins or loses
 */
public class TokimonFinder {
    public static void main(String[] args) {

        System.out.println("                        --------------------");
        System.out.println("                        TOKIMON FINDER GAME ");
        System.out.println("                        --------------------");
        System.out.println("Rules:\n" +
                "1. OBJECTIVE: avoid evil Fokimon (X) and collect all Tokimon ($) on the board\n" +
                "2. GAMEPLAY: to cast a spell click X, to move use W (UP) / A (LEFT) / S (DOWN) / D (RIGHT)\n" +
                "3. GAMEEND: once player finds all Tokimon and wins OR player lands on en evil Fokimon and loses!\n");


        String numToki = null;
        String numFoki = null;
        boolean cheatMode = false;

        for (String arg : args) {
            if (arg.startsWith("--numToki=")) {
                numToki = arg.substring(10);
            } else if (arg.startsWith("--numFoki=")) {
                numFoki = arg.substring(10);
            } else if (arg.equals("--cheat")) {
                cheatMode = true;
            }
        }

        int tokis = GameSetup.setTokimons(numToki);
        int fokis= GameSetup.setFokimons(numFoki);
        //cheatMode = true;

        Player player = new Player();
        GameBoard gameBoard = new GameBoard(tokis,fokis);
        gameBoard.placeTokiAndFoki();
        if(cheatMode) {
            CheatMode.displayCheatBoard(gameBoard);
        } else {
            gameBoard.displayBoard(player);
        }

        // get initial position
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter initial position (ex: B5)");
        String initialPosition = scanner.nextLine();
        boolean correctPosition = false;
        while (!correctPosition) {
            try {
                player.setPosition(initialPosition);
                correctPosition = true;
                gameBoard.updateBoard(player);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid position, please try again: <letter A-J> <number 1-10> (ex: B5)");
                gameBoard.displayBoard(player);
                initialPosition = scanner.nextLine();
            }
        }
        //gameBoard.displayBoard(player);

        //play game
        while (!gameBoard.isGameOver()) {
            gameBoard.displayBoard(player);
            System.out.println("Tokimons collected: " + player.getTokimonScore() + " / " + tokis);
            System.out.println("Enter move: (press x to use a spell)");
            String move = scanner.nextLine().toLowerCase();
            // if statement for spells
            if(move.equals("x") || move.equals("X")) {
                int numSpells = player.getSpells();
                Spell.displaySpells(numSpells);
                if(numSpells > 0) {
                    try {
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        switch (choice) {
                            case 1:
                                Spell.castSpell1(player, gameBoard);

                                break;
                            case 2:
                                Spell.castSpell2(player, gameBoard);
                                //numSpells--;
                                //player.setSpells(numSpells);
                                break;
                            case 3:
                                Spell.castSpell3(player, gameBoard);
                                break;
                            default:
                                System.out.println("Invalid spell option");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid spell option");
                    }
                }
            } else {
                player.move(gameBoard, move);
                gameBoard.updateBoard(player);
                // if they win the game
                if (player.getTokimonScore() == tokis) {
                    System.out.println("Yay! You won!");
                    gameBoard.displayFullBoard(player);
                    break;
                }
            }

        }

        System.out.println("Thanks for playing");
    }

}
