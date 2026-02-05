package com.example.card_pexeso;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

public class HelloController {
    @FXML
    private Label labelWinner;

    @FXML
    private GridPane gridPane;
    @FXML
    private Label labelScore1;
    @FXML
    private Label labelScore2;
    @FXML
    private Label labelKteryHracHraje;



    private ArrayList<Card> cards = new ArrayList<>();
    private Card firstCard;
    private Card secondCard;
    private boolean canFlip = true;

    private int currentPlayer = 1;
    private int winnerPlayer = 0;
    private int score1 = 0;
    private int score2 = 0;




    @FXML
    public void generateCards() {
        for(int i = 0; i < 8; i++) {
            cards.add(new Card(i));
            cards.add(new Card(i));
        }
    }

    @FXML
    public void displayCards() {
        int index = 0;

        for(int row = 1; row < 5; row++){
            for(int col = 0; col < 4; col++){
                Card card = cards.get(index++);
                Button btn = card.getButton();
                btn.setOnAction( e -> handleCardClick(card));
                gridPane.add(btn, col, row);
            }
        }

    }

    private void handleCardClick ( Card card ) {
        if ( firstCard == null ) {
            firstCard = card ;
            card.flip();
        } else {
            secondCard = card ;
            card.flip();
            checkMatch() ;
        }
    }

    public void checkMatch() {
        if(firstCard != secondCard) {
            if((firstCard.getId()) == (secondCard).getId()) {
                if(currentPlayer == 1) {
                    score1++;
                }else{
                    score2++;
                }
                firstCard.getButton().setDisable(true);
                secondCard.getButton().setDisable(true);
                firstCard = null;
                secondCard = null;
            }else{
                if(currentPlayer == 1) {
                    currentPlayer = 2;
                }else{
                    currentPlayer = 1;
                }
                firstCard.flipBack();
                secondCard.flipBack();

                firstCard = null;
                secondCard = null;

            }

            labelScore1.setText(score1+"");
            labelScore2.setText(score2+"");
            if((score1+score2) == 8){
                if(score1 > score2){
                    winnerPlayer = 1;
                }else{
                    winnerPlayer = 2;
                }
                labelWinner.setText("Vyhrál hráč " +winnerPlayer+ "!");
            }else{
                labelKteryHracHraje.setText("Hraje hráč " +currentPlayer);
            }

        }


    }

    @FXML
    public void initialize() {
        generateCards();
        Collections.shuffle(cards);
        displayCards();
    }

















}
