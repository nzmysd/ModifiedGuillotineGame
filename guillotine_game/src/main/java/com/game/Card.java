package com.game;

/** Nozomi Yasuda
 * Card.java
 *
 * <code>Card</code> represents a playing card.
 */
public class Card {
  
  /**
   * String value that holds the name of the card
   */
  private String name;
  
  /**
   * String value that holds the group of the card
   */
  private String group;
  
  /**
   * int value that holds the point value.
   */
  private int pointValue;
  
  
  /**
   * Creates a new <code>Card</code> instance.
   *
   * @param cardName  a <code>String</code> value
   *                  containing the name of the card
   * @param cardGroup  a <code>String</code> value
   *                  containing the group of the card
   * @param cardPointValue an <code>int</code> value
   *                  containing the point value of the card
   */
  public Card(String cardName, String cardGroup, int cardPointValue) {
    name = cardName;
    group = cardGroup;
    pointValue = cardPointValue;
  }
  
  public void setValue(int x){
    this.pointValue = x;
  }
  
  
  /**
   * Accesses this <code>Card's</code> name.
   * @return this <code>Card's</code> name.
   */
  public String name() {
    return name;
  }
  
  /**
   * Accesses this <code>Card's</code> group.
   * @return this <code>Card's</code> group.
   */
  public String group() {
    return group;
  }
  
  /**
   * Accesses this <code>Card's</code> point value.
   * @return this <code>Card's</code> point value.
   */
  public int pointValue() {
    return this.pointValue;
  }
  
  /*
   * Increments the value of card
   */
  public void incrementValue(){
    this.pointValue++;
  }
  
  /*
   * Decrements the value of card
   */
  public void decrementValue(){
    this.pointValue--;
  }
  
  /*
   * The display of each card in line of cards
   */
  public String displayCard(){
    String str = "";
    str = str+this.name()+"\nGroup: "+this.group()+"\nPoints: ";
    boolean specialRules = 
      (this.name().equals("Count") 
         || this.name().equals("Countess")
         || this.name().equals("Lady")
         || this.name().equals("Lord")
         || this.name().equals("Heretic")
         || this.name().equals("Tax Collector")
         || this.name().equals("Tragic Figure")
         || this.name().equals("Palace Guard"));
    if(specialRules) str += "*";
    else str += ""+this.pointValue();
    return str;
  }
  
  /*
   * Display of cards in players hand
   */
  public String displayHandCard(){
    String str = "";
    str = str+this.name()+"\nGroup: "+this.group()+"\nPoints: ";
    str += ""+this.pointValue();
    return str;
  }
  
  /**
   * Converts the name, group, and point value into a string in the format
   *     "[Name] of [Group] (point value = [PointValue])".
   * This provides a useful way of printing the contents
   * of a <code>Deck</code> in an easily readable format or performing
   * other similar functions.
   *
   * @return a <code>String</code> containing the name, group, 
   *         and point value of the card.
   */
  @Override
  public String toString() {
    return this.name() + " of " + this.group() + " (point value = " + this.pointValue() + ")";
  }
}