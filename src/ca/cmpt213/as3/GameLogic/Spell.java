package ca.cmpt213.as3.GameLogic;

import ca.cmpt213.as3.UI.GameBoard;

import java.util.Scanner;

/**
 * in this class:
 * gives the player options to cast spells:
 * Spell 1: allow the player to jump to another grid location
 * Spell 2: randomly reveal the location of one Tokimon
 * Spell 3: randomly eliminate one Fokimon
 */

public class Spell {
    static boolean spell1 = true;
    static boolean spell2 = true;
    static boolean spell3 = true;

    public static void displaySpells(int spells) {
        if(spells <= 0) {
            System.out.println("No more spells left");
        } else {
            System.out.println("Enter the number to cast a spell:");
            if (spell1)
                System.out.println("1 - Jump to another location");
            if (spell2)
                System.out.println("2 - Randomly reveal the location of a Fokimon");
            if (spell3)
                System.out.println("3 - Randomly eliminate a Fokimon");
        }
    }

    // allow the player to jump to another grid location
    public static void castSpell1(Player player, GameBoard gameBoard){
        if(spell1) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter a new position (ex: B5)");
            String newPosition = scanner.nextLine();
            boolean correctPosition = false;
            while (!correctPosition) {
                try {
                    player.setPosition(newPosition);
                    correctPosition = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid position, please try again: <letter A-J> <number 1-10> (ex: B5)");
                    newPosition = scanner.nextLine();
                    gameBoard.displayBoard(player);
                }
            }
            player.setSpells(player.getSpells() - 1);
            spell1 = false;
        } else {
            System.out.println("This spell is already used");
        }
    }

    // randomly reveal the location of one Tokimon
    public static void castSpell2(Player player, GameBoard gameBoard){
        if(spell2) {
            System.out.println("Random Tokimon revealed");
            gameBoard.revealTokimon();
            player.setSpells(player.getSpells() - 1);
            spell2 = false;
        } else {
            System.out.println("This spell is already used");
        }
    }

    // randomly eliminate one Fokimon
    public static void castSpell3(Player player, GameBoard gameBoard){
        if(spell3){
            System.out.println("Random Fokimon removed");
            gameBoard.removeFokimon(gameBoard);
            player.setSpells(player.getSpells() - 1);
            spell3 = false;
        } else {
            System.out.println("This spell is already used");
        }
    }
}
