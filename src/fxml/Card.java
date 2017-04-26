/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import domein.Kaart;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Edward
 */
public class Card {

    private StackPane pane;

    public Card(String omschrijving, String kleur) {
        pane = new StackPane();
        pane.setMinSize(0, 0);
        pane.setPrefSize(80, 100);
        pane.setMaxSize(80, 100);
        pane.setStyle("-fx-background-color: " + kleur + ";");
        
        Label label = new Label(omschrijving);
        label.setStyle("-fx-background-color: black; "
                + "-fx-text-fill: white;");
        
        pane.getChildren().add(label);
    }
    
    public Pane getContent(){
        return pane;
    }

}
