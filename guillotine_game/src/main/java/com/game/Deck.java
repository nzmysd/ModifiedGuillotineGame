package com.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/** Nozomi Yasuda
 * The Deck class reads in the card types from cards.txt.
 */
public class Deck {
  //
  private String namesArray[];
  private String groupsArray[];
  private String pointValuesArray[];
  private int valuesArray[];
  
  /*
   * Initializing a new deck
   */
  public Deck() {
    namesArray = new String[40];
    groupsArray = new String[40];
    pointValuesArray = new String[40];
    valuesArray = new int[40];
    int i=0;
    
    /*
     * Reading in from card.txt
     */
    try{
      File cardFile = new File("guillotine_game\\lib\\cards.txt");
      Scanner myScanner = new Scanner(cardFile);
      while(myScanner.hasNextLine()){
        String temp = myScanner.nextLine();
        String[] tempData = temp.split(":",3);
        namesArray[i] = tempData[0];
        groupsArray[i] = tempData[1];
        pointValuesArray[i] = tempData[2];
        i++;
      }
      myScanner.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    for(int j=0;j<40;j++){
      try {
        valuesArray[j] = Integer.parseInt(pointValuesArray[j]);
      }
      catch (final NumberFormatException e) {
        valuesArray[j] = 0;
      }
      if(5<=j && j<=8) valuesArray[j] = 2; //Hand initializing royal values
    }
    
  }
  
  //Returns names of Cards in deck
  public String[] getNamesArray(){
    return namesArray;
  }
  
  //Returns names of Groups in deck
  public String[] getGroupsArray(){
    return groupsArray;
  }
  
  //Returns names of Point worth in deck
  public String[] getPointValuesArray(){
    return pointValuesArray;
  }
  
  //Returns names of Card values in deck
  public int[] getValuesArray(){
    return valuesArray;
  }
  
}
