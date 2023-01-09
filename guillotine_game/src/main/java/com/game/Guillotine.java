package com.game;
import com.linkedlist.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/** Nozomi Yasuda
 * A Java FX Application for game Guillotine
 */
public class Guillotine extends Application {
  private static Game game = new Game();

  //Buttons for actions of player 1
  private Button[] btns1 = new Button[10];
  
  //Buttons for actions of player 2
  private Button[] btns2 = new Button[10];
  
  //Handles for bunttons of player 1
  ButtonClickEvent1 eventHandler1 = new ButtonClickEvent1();
  
  //Handles for bunttons of player 2
  ButtonClickEvent2 eventHandler2 = new ButtonClickEvent2();
  
  //Field for the pop-up
  private static BorderPane borderPane = new BorderPane();
  
  //Field for players cards and scores
  private static BorderPane subBorderPane = new BorderPane();
  
  //Field for buttons for actions of player 1
  private static VBox vboxLeft  = new VBox(10);
  
  //Field for buttons for actions of player 2
  private static VBox vboxRight = new VBox(10);
  
  
  @Override
  public void start(Stage primaryStage) {
    borderPane.setPrefSize(750,750);
    
    //Player 1 VBox
    vboxLeft.setPrefWidth(300);
    vboxLeft.getChildren().add(new Label("Player 1"));
    int[] indices = new int[10];
    for(int i = 0; i < btns1.length; i++) {
      indices[i]=i;
      Button btn = new Button((i+1) + ". " + game.actionStrings[i]);
      btn.setMinWidth(vboxLeft.getPrefWidth());
      vboxLeft.getChildren().add(btn);
      btn.setOnAction(eventHandler1);
      btns1[i] = btn;
    }
    displayHand1();
    
    
    //Player 2 VBox
    vboxRight.setPrefWidth(300);
    vboxRight.getChildren().add(new Label("Player 2"));
    for(int i = 0; i < btns2.length; i++) {
      Button btn = new Button((i+1) + ". " + game.actionStrings[i]);
      btn.setMinWidth(vboxRight.getPrefWidth());
      vboxRight.getChildren().add(btn);
      btn.setOnAction(eventHandler2);
      btns2[i] = btn;
    }
    displayHand2();
    
    
    //line of cards ScrollPane
    displayLOC();
    displayScore();
    
    //layout
    borderPane.setLeft(vboxLeft);
    borderPane.setRight(vboxRight);
    
    
    Scene scene = new Scene(borderPane);
    primaryStage.setTitle("Guillotine");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  /**
   * A method to launch the GUI
   * @param args the command line arguments to pass to the GUI
   */
  public static void launchGUI(String[] args) {
    Application.launch(args);
  }
  
  
  /**
   * The main method launches the applications
   * @param String[] args
   */
  public static void main(String[] args) {
    Thread thread = new Thread() {
      public void run() {
        Guillotine.launchGUI(args);
      }
    };
    thread.start();
  }
  
  /*
   * Handles action button clicking for player 1
   */
  private class ButtonClickEvent1 implements EventHandler<ActionEvent>{
    public void handle(ActionEvent e){
      if(game.player1sTurn()){
        for(int i=0;i<10;i++){
          if(e.getSource() == btns1[i]){
            boolean endTurn = false;
            endTurn = game.loc().performAction(game.player1(),i+1);
            if (i<9) btns1[i].setDisable(true);
            if(endTurn)game.nextRound(); 
          }
        }
        displayLOC();
        displayHand1();
        displayScore();
      }
    }
  }
  
  /*
   * Handles action button clicking for player 2
   */
  private class ButtonClickEvent2 implements EventHandler<ActionEvent>{
    public void handle(ActionEvent e){
      if(!game.player1sTurn()){
        for(int i=0;i<10;i++){
          if(e.getSource() == btns2[i]){
            boolean endTurn = false;
            endTurn = game.loc().performAction(game.player2(),i+1);
            if (i<9)btns2[i].setDisable(true);
            if(endTurn)game.nextRound(); 
          }
        }
        displayLOC();
        displayHand2();
        displayScore();
      }
    }
  }
  
  /*
   * Displays line of cards
   */
  private static void displayLOC(){
    ScrollPane scrollPane = new ScrollPane();
    VBox vboxCenter = new VBox();
    scrollPane.setContent(vboxCenter);
    vboxCenter.getChildren().add(new Label("Line Of Cards size = " + game.loc().size()));
    if(!game.loc().isEmpty()){
      //cards
      LLNode<Card> curr = game.loc().getCards().getFirstNode();
      while(curr!=null){
        Label cardLabel = new Label(curr.getElement().displayCard());
        cardLabel.setStyle("-fx-border-color:black");
        cardLabel.setMinWidth(150);
        vboxCenter.getChildren().add(cardLabel);
        curr = curr.getNext();
      }
      borderPane.setCenter(scrollPane);
    }
    else vboxCenter.getChildren().add(new Label("END OF GAME"));
  }
  
  
  /*
   * Displays hand of player 1
   */
  private static void displayHand1(){
    ScrollPane scrollPane = new ScrollPane();
    VBox vboxHand1 = new VBox();
    scrollPane.setContent(vboxHand1);
    scrollPane.setPrefSize(300,400);
    vboxHand1.getChildren().add(new Label("Player 1's Cards:"));
    if(!game.loc().isEmpty()){ 
      LLNode<Card> curr = game.player1().getHand().getCards().getFirstNode();
      while(curr!=null){
        Label cardLabel = new Label(curr.getElement().displayHandCard());
        cardLabel.setStyle("-fx-border-color:black");
        cardLabel.setMinWidth(150);
        vboxHand1.getChildren().add(cardLabel);
        curr = curr.getNext();
      } 
      subBorderPane.setLeft(scrollPane);
      borderPane.setBottom(subBorderPane);
    }
    else{
      System.out.println("END OF GAME");
    }
  }
  
  /*
   * Displays hand of player 2
   */
  private static void displayHand2(){
    ScrollPane scrollPane = new ScrollPane();
    VBox vboxHand2 = new VBox();
    scrollPane.setContent(vboxHand2);
    scrollPane.setPrefSize(300,400);
    vboxHand2.getChildren().add(new Label("Player 2's Cards:"));
    if(!game.loc().isEmpty()){
      LLNode<Card> curr = game.player2().getHand().getCards().getFirstNode();
      while(curr!=null){
        Label cardLabel = new Label(curr.getElement().displayHandCard());
        cardLabel.setStyle("-fx-border-color:black");
        cardLabel.setMinWidth(150);
        vboxHand2.getChildren().add(cardLabel);
        curr = curr.getNext();
      }
      subBorderPane.setRight(scrollPane);
      borderPane.setBottom(subBorderPane);
    }
    else {
      System.out.println("END OF GAME");
    }
  }
  
  /*
   * Displays score of players
   */
  private static void displayScore(){
    VBox vboxScore = new VBox();
    vboxScore.setPrefSize(300,400);
    vboxScore.getChildren().add(new Label(game.roundDisplay()));
    vboxScore.getChildren().add(new Label("Player 1's current score: "+game.player1().getHand().getPoints()));
    vboxScore.getChildren().add(new Label("Player 2's current score: "+game.player2().getHand().getPoints()));
    
    subBorderPane.setCenter(vboxScore);
  }
  
}

