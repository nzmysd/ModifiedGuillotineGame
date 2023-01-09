package com.game;
/** Nozomi Yasuda
 * Class player represents a player in the game guillotine
 */

 public class Player {
    //Name of the player
    private String playerName;
    
    //Actions available to player
    private boolean[] actionsAvailable = new boolean[10];
    
    //Points total
    private int points;
    
    //Cards in hand
    private Hand hand;
    
    /*
     * Initializes new player
     */
    public Player(String name){
      for(int i=0;i<10;i++){
        actionsAvailable[i] = true; 
      }
      points = 0;
      hand = new Hand();
      playerName = name;
    }
    
    /*
     * Set the actions as used
     */
    public void setActionUsed(int index){
      if (index <= 9) actionsAvailable[index] = false;
    }
    
    /*
     * Returns name of player
     */
    public String getName(){
      return this.playerName;
    }
    
    /*
     * Returns hand of player
     */
    public Hand getHand(){
      return this.hand;
    }
    
    /*
     * Sets hand of player
     */
    public void setHand(Hand hand){
      this.hand = hand;
    }
    
    /*
     * Sets point of player
     */
    public void setPoints(int x){
      this.points = x;
    }
    
    /*
     * Returns points of player
     */
    public int getPoints(){
      return this.points;
    }
    
    /*
     * Returns actions as a string
     */
    public String actionsString() {
      String str = "";
      for(int i=0;i<10;i++){
        if(actionsAvailable[i]) str+= ((i+1)+" ");
      }
      return str;
    }
    
    
  }
  