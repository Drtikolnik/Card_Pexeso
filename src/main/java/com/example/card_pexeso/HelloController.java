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


    private ArrayList<Card> cards = new ArrayList<>();
    private Card firstCard;
    private Card secondCard;
    private boolean canFlip = true;

    private int currentPlayer = 1;
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
        gridPane.getChildren().clear();
        int index = 0;

        for(int row = 0; row < 4; row++){
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
        if((firstCard.getId()) == (secondCard).getId()) {
            firstCard = null;
            secondCard = null;
        }else{

            firstCard.flipBack();
            secondCard.flipBack();

            firstCard = null;
            secondCard = null;

        }

    }

    @FXML
    public void initialize() {
        generateCards();
        //Collections.shuffle(cards);
        displayCards();
    }

















}
