package com.example.card_pexeso;

import javafx.application.Application;
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
        this.button.setMinSize(60,60);
    }

    public void flip () {
        button.setText(String.valueOf(id)); ;
    }


    public void flipBack () {
        button.setText("?") ;
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



