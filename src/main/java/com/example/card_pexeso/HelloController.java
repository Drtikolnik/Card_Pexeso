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
    private Label labelScore3;

    @FXML
    private Label labelTah1;
    @FXML
    private Label labelTah2;
    @FXML
    private Label labelTah3;

    @FXML
    private Label labelKteryHracHraje;
    @FXML
    private Button buttonHratZnovu;





    private ArrayList<Card> cards = new ArrayList<>();
    private Card firstCard;
    private Card secondCard;
    private boolean canFlip = true;

    private int currentPlayer = 1;
    private int winnerPlayer = 0;
    private int score1 = 0;
    private int score2 = 0;
    private int score3 = 0;
    private int tah1 = 0;
    private int tah2 = 0;
    private int tah3 = 0;




    @FXML
    public void generateCards() {
        for(int i = 0; i < 12; i++) {
            cards.add(new Card(i));
            cards.add(new Card(i));
        }
        cards.add(new Card(100));
    }

    @FXML
    public void displayCards() {
        int index = 0;

        for(int row = 1; row < 6; row++){
            for(int col = 0; col < 5; col++){
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
        chceckSpecial();
    }



    public void checkMatch() {
        chceckSpecial();
        if(firstCard != secondCard) {
            if((firstCard.getId()) == (secondCard).getId()) {
                if(currentPlayer == 1) {
                    score1++;
                    tah1++;
                }else if(currentPlayer == 2) {
                    score2++;
                    tah2++;
                }else{
                    score3++;
                    tah3++;
                }
                firstCard.getButton().setDisable(true);
                secondCard.getButton().setDisable(true);

            }else{
                if(currentPlayer == 1) {
                    tah1++;
                    currentPlayer = 2;
                }else if(currentPlayer == 2) {
                    tah2++;
                    currentPlayer = 3;
                }else{
                    tah3++;
                    currentPlayer = 1;
                }
                firstCard.flipBack();
                secondCard.flipBack();

            }

            firstCard = null;
            secondCard = null;
            updateScoreAndWinner();
            updateTah();

        }

    }
    public void chceckSpecial(){
        if (firstCard.getId()==100) {
            if(currentPlayer == 1) {
                score1 = score1 + 3;
            }else if(currentPlayer == 2) {
                score2 =  score2 + 3;
            }else{
                score3 = score3 + 3;
            }
            firstCard.getButton().setDisable(true);
            firstCard = null;

        }else if(secondCard.getId()==100){
            if(currentPlayer == 1) {
                score1 = score1 + 3;
            }else if(currentPlayer == 2) {
                score2 =  score2 + 3;
            }else{
                score3 = score3 + 3;
            }
            secondCard.getButton().setDisable(true);
            secondCard = null;
        }
        updateTah();
        updateScoreAndWinner();

    }

    public void updateScoreAndWinner() {
        labelScore1.setText(score1+"");
        labelScore2.setText(score2+"");
        labelScore3.setText(score3+"");
        if((score1+score2+score3) == 8){
            buttonHratZnovu.setText("HRÁT ZNOVU");
            //buttonHratZnovu.setVisible(true);
            //buttonHratZnovu.setDisable(false);
            if(score1 > score2 && score1 > score3) {
                winnerPlayer = 1;
                labelWinner.setText("Vyhrál hráč 1!");

            } else if(score2 > score1 && score2 > score3) {
                winnerPlayer = 2;
                labelWinner.setText("Vyhrál hráč 2!");

            }else if(score3 > score1 && score3 > score2) {
                winnerPlayer = 3;
                labelWinner.setText("Vyhrál hráč 3!");

            }else{
                labelWinner.setText("Remíza!");

            }
        }else{
            labelKteryHracHraje.setText("Hraje hráč " +currentPlayer);
        }
    }

    public void updateTah(){
        labelTah1.setText(tah1+"");
        labelTah2.setText(tah2+"");
        labelTah3.setText(tah3+"");
        //tahy počítám v checkMatch
    }

    public void hratZnovu(){
        //buttonHratZnovu.setVisible(false);
        //buttonHratZnovu.setDisable(true);
        buttonHratZnovu.setText("RESTART");
        cards.clear();
        firstCard = null;
        secondCard = null;
        currentPlayer = 1;
        winnerPlayer = 0;
        score1 = 0;
        score2 = 0;
        score3 = 0;
        tah1 = 0;
        tah2 = 0;
        tah3 = 0;
        updateTah();
        labelScore1.setText(score1+"");
        labelScore2.setText(score2+"");
        labelScore3.setText(score3+"");
        labelWinner.setText("");
        initialize();
    }

    @FXML
    public void initialize() {
        generateCards();
        Collections.shuffle(cards);
        displayCards();
    }

















}
