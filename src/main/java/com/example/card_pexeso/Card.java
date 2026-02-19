package com.example.card_pexeso;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import javafx.application.Application;
import javax.swing.Timer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;

import java.awt.*;


public class Card {
    private int id ;
    private Button button ;
    private boolean matched = false;
    private final Image frontImage;
    private final Image backImage;

    public Card(int id) {
        this.id = id;
        this.button = new Button(null);
        this.button.setMinSize(150,150);
        this.frontImage = loadImage("/com/example/card_pexeso/imgs/"+id+".png");
        this.backImage = loadImage("/com/example/card_pexeso/imgs/Terraria2.png");
        button.setGraphic(imageView(backImage));


    }

    public void flip () {
        button.setGraphic(imageView(frontImage));
        button.setText(null);
    }

    private Image loadImage(String path) {
        var stream = getClass().getResourceAsStream(path);
        if (stream == null){
            throw new IllegalArgumentException("Can't load image from "+path);
        }
        return new Image(stream);
    }

    private ImageView imageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(120);
        imageView.setFitWidth(120);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    public void flipBack() {
        // 1. Vytvoříme pauzu na 1 sekundu
        PauseTransition pause = new PauseTransition(Duration.seconds(1));

        // 2. Co se stane po skončení pauzy
        pause.setOnFinished(e -> {
            // Místo otazníku nastavíme obrázek pozadí (Terraria.png)
            button.setGraphic(imageView(backImage));
            button.setText(null); // Pro jistotu smažeme případný text
        });

        // 3. Spustíme
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



