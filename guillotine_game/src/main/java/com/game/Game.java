package com.game;

/** Nozomi Yasuda
 * The class game represents a game of guillotine
 */
public class Game {
    //
    private Player player1;
    private Player player2;
    private LineOfCards loc;
    private int round = 0;
    private boolean player1sTurn;
    public final String[] actionStrings = {
      "Move the lead card back 4 places",
      "Move the lead card back 3 places",
      "Move the lead card back 2 places",
      "Move the lead card back 1 place",
      "Move the lead card to the end of the line",
      "Move the last card of the line to the front",
      "Reverse the entire line of cards",
      "Reverse the first 5 cards of the line",
      "Skip the turn (ends turn)",
      "Take the first card from the line (ends turn)"
    };
    
    //Initializes a game 
    public Game(int size){
      player1 = new Player("Player 1");
      player2 = new Player("Player 2");
      loc = new LineOfCards(size);
      round = 0;
      player1sTurn = true;
    }

    public Game(){
      player1 = new Player("Player 1");
      player2 = new Player("Player 2");
      loc = new LineOfCards(20);
      round = 0;
      player1sTurn = true;
    }

    public LineOfCards loc(){
      return loc;
    }
    public Player player1(){
      return player1;
    }
    public Player player2(){
      return player2;
    }
    public int round(){
      return round;
    }
    public String roundDisplay(){
      String str;
      if(player1sTurn()) str = "Player 1's turn.";
      else str = "Player 2's turn.";
      return "ROUND "+round()+". "+str;
    }
    public void nextRound(){
      round++;
      player1sTurn = !player1sTurn;
    }
    public boolean player1sTurn(){
      return player1sTurn;
    }
  }