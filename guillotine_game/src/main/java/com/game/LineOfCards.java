package com.game;

import java.util.Collections;

import com.linkedlist.*;

import java.util.ArrayList;
/** Nozomi Yasuda
 * The LineofCards class represents a line of persons that player selects from.
 */
public class LineOfCards{
  /*
   * Cards contains all the cards in the line of cards.
   */
  private LinkedList<Card> cards;
  
  /*
   * Size is the number of not-yet-dealt cards.
   * There are 20 cards initially. 
   * The next card to be dealt is at size - 1.
   */
  private int size;
  
  /*
   * Creates a new Line of Cards instance. It draws n cards at random 
   * and creates a linked list of cards. 
   * @param n is the number of cards in the line of cards.
   */
  public LineOfCards(int n) {
    cards = new LinkedList<Card>();
    Deck fullDeck = new Deck();
    String[] names = fullDeck.getNamesArray();
    String[] groups = fullDeck.getGroupsArray();
    int[] values = fullDeck.getValuesArray();
    
    ArrayList<Card> cardList = new ArrayList<Card>();
    for(int i=0;i<40;i++){
      cardList.add(new Card(names[i],groups[i],values[i]));
    }
    Collections.shuffle(cardList);
    
    for(int i = 0; i < n; i++ ){
      Card newCard = cardList.get(i);
      cards.addToFront(newCard);
    }
    
    size = n;
  }
  
  public LinkedList<Card> getCards(){
    return this.cards;
  }
  
  /*
   * Determines if this line of cards is empty (no undealt cards).
   * @return true if this line of cards is empty, false otherwise.
   */
  public boolean isEmpty() {
    return this.size()==0;
  }
  
  /*
   * Accesses the number of undealt cards in this line of cards.
   * @return the number of undealt cards in this line of cards.
   */
  public int size() {
    return this.size;
  }
  
  /*
   * Updates line of card according to action
   * @param Player who is performing action
   * @param optionNumber is the number of action
   */
  public boolean performAction(Player player, int optionNumber){
    boolean endTurn = false;
    switch(optionNumber) {
      case 1:
        cards.moveBack(4);
        break;
      case 2: 
        cards.moveBack(3);
        break;
      case 3: 
        cards.moveBack(2);
        break;
      case 4:
        cards.moveBack(1);
        break;
      case 5:
        cards.moveFirstToLast();
        break;
      case 6:
        cards.moveLastToFirst();
        break;
      case 7: 
        cards.reverseList();
        break;
      case 8: 
        cards.reverseFirstK(5);
      case 9: 
        endTurn = true;
        break;
      case 10:
        endTurn = true;
        Card tempCard = cards.removeFromFront();
        player.getHand().addToHand(tempCard);
        player.setPoints(player.getHand().getPoints());
        size--;
        break;
    }
    if(optionNumber < 10) player.setActionUsed(optionNumber);
    return endTurn;
  }
  
  /**
   * Generates and returns a string representation of this line of cards.
   * @return a string representation of this line of cards.
   */
  @Override
  public String toString() {
    String reString = "";
    LLNode<Card> curr = cards.getFirstNode();
    int i=0;
    while(curr!=null){
      Card currCard = curr.getElement();
      reString += (i+1) + ". " + currCard.toString() + "\n";
      curr = curr.getNext();
      i++;
    }
    return reString;
    
  }
  
}

