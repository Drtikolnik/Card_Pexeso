package com.example.card_pexeso;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import javafx.application.Application;
import javax.swing.Timer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import javafx.scene.control.Button;


public class Card {
    private int id ;
    private Button button ;
    private boolean matched = false ;

    public Card(int id) {
        this.id = id;
        this.button = new Button("?");
        this.button.setMinSize(150,150);
    }

    public void flip () {
        button.setText(String.valueOf(id)); ;
    }


    public void flipBack () {
        // Vytvoříme pauzu na 1 sekundu
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        // Co se má stát, až pauza skončí:
        pause.setOnFinished(e -> button.setText("?"));

        // Spustíme odpočet
        pause.play();
    }




    public int getId() {
        return id;
    }

    public Button getButton() {
        return button;
    }

    public boolean isMatched() {
        return matched;
    }
}



