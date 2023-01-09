package com.game;
import com.linkedlist.*;

/** Nozomi Yasuda
 * The Hand class represents the hand of cards the player currently owns.
 */
public class Hand {
    /**
     * Cards that the player is currently holding
     */
    private LinkedList<Card> hand;
    
    //The card Count that the player owns, null otherwise
    private Card count;
    
    //The card Countess that the player owns, null otherwise
    private Card countess;
    
    //The card Lady that the player owns, null otherwise
    private Card lady;
    
    //The card Lord that the player owns, null otherwise
    private Card lord;
    
    //The card Heretic that the player owns, null otherwise
    private Card heretic;
    
    //The card TaxCollector that the player owns, null otherwise
    private Card taxCollector;
    
    //The card Palace Guards that the player owns, null otherwise
    private Card[] palaceGuards;
    
    //The card Tragic Figures that the player owns, null otherwise
    private Card tragicFigure;
    
    //The number of Church cards the player owns
    private int numChurchCards;
    
    //The number of Civic cards the player owns
    private int numCivicCards;
    
    //The number of Palace Guards cards the player owns
    private int numPalaceGuards;
    
    //The number of Commoner cards the player owns
    private int numCommoners;
    
    /**
     * Creates a new hand, an empty linked list. 
     */
    public Hand(){
      hand = new LinkedList<Card>();
      count = null;
      countess = null;
      lady = null;
      lord = null;
      heretic = null;
      taxCollector = null;
      tragicFigure = null;
      palaceGuards = new Card[5];
      numChurchCards = 0;
      numCivicCards = 0;
      numPalaceGuards = 0;
      numCommoners = 0;
    }
    
    /*
     * Adds card to player's hand and updates special values
     * @param newCard is the card to add
     */
    public void addToHand(Card newCard){
      hand.addToFront(newCard);
      
      String newName = newCard.name();
      String newGroup = newCard.group();  
      
      if(newGroup.equals("Church")){
        if(heretic!=null) heretic.incrementValue();
        numChurchCards++;  
      }
      if(newGroup.equals("Civic")){
        if(taxCollector !=null) taxCollector.incrementValue();
        numCivicCards++;
      }
      if(newGroup.equals("Commoner ")){
        if(tragicFigure!=null) tragicFigure.decrementValue();
        numCommoners++;
      }
      if(newName.equals("Palace Guard")) numPalaceGuards++;
      
      if(newName.equals("Count")){
        count = newCard;
        if(countess==null) newCard.setValue(2);
        else{               
          newCard.setValue(4);
          countess.setValue(4);
        }
      }
      
      if(newName.equals("Countess")){
        countess = newCard;
        if(count==null) newCard.setValue(2);
        else{               
          newCard.setValue(4);
          count.setValue(4);
        }
      }  
      if(newName.equals("Lady")){
        lady = newCard;
        if(lord==null) newCard.setValue(2);
        else{      
          newCard.setValue(4);
          lord.setValue(4);
        }
      }  
      if(newName.equals("Lord")){
        lord = newCard;
        if(lady==null) newCard.setValue(2);
        else{
          newCard.setValue(4);
          lady.setValue(4);
        }
      }
      if(newName.equals("Heretic")) {
        heretic = newCard;
        newCard.setValue(numChurchCards);
      }
      
      if(newName.equals("Tax Collector")) {
        taxCollector = newCard;
        newCard.setValue(numCivicCards);
      }
      
      if(newName.equals("Palace Guard")){
        palaceGuards[(numPalaceGuards-1)] = newCard;
        for(int i=0;i<numPalaceGuards;i++){
          palaceGuards[i].setValue(numPalaceGuards);
        }
      }
      
      if(newName.equals("Tragic Figure")){
        tragicFigure = newCard;
        newCard.setValue(numCommoners*-1);
      }
      
    }
    
    /*
     * Calculates points of players
     */
    public int getPoints(){
      LLNode<Card> curr = this.hand.getFirstNode();
      int points = 0;
      while(curr!=null){
        points += curr.getElement().pointValue();
        curr = curr.getNext();
      }
      return points;
    }
    
    /*
     * Returns cards in hand
     */
    public LinkedList<Card> getCards()
    {
      return this.hand;
    }
    
    
    /*
     * Generates and returns a string representation of this line of cards.
     * @return a string representation of this line of cards.
     */
    @Override
    public String toString() {
      String reString = "";
      LLNode<Card> curr = hand.getFirstNode();
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
  
  