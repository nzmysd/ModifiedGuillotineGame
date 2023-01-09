package com.tester;

import java.util.Scanner;

import com.game.LineOfCards;
import com.game.Player;


/** Nozomi Yasuda
 * Tester class for testing game
 */
public class Tester{
  private static LineOfCards loc;
  private static int round = 0;
  public static void main(String[] args) {
    int lineOfCardsSize = 10;
    
    Player player1 = new Player("Player 1");
    Player player2 = new Player("Player 2");
    loc = new LineOfCards(lineOfCardsSize);
    
    while(!loc.isEmpty()){
      round++;
      Player temp = new Player("temp");
      if (round%2!=0) {//odd round
        temp = player1;
      }
      else{
        temp = player2;
      }
      loc = performTurn(temp);
    }
    System.out.println("====Game Over.====");
    
  }
  
  /*
   * Test for game actions and cards in turns
   */
  public static LineOfCards performTurn(Player temp){
    Scanner in = new Scanner(System.in);
    boolean endTurn = false;
    while (!endTurn){
      System.out.println(loc);
      System.out.print(temp.getName());
      System.out.println(", choose one of the following actions: ");
      System.out.println(temp.actionsString());
      String s = in.nextLine();
      int actionNumber = Integer.parseInt(s);
      endTurn = loc.performAction(temp,actionNumber);
      System.out.println("current points:");
      System.out.println(temp.getPoints());
    }
    System.out.println("====Turn Ended.====");
    in.close();
    return loc;
  }
}
