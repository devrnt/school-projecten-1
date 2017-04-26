/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fxml;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Edward
 */
public class Card {

    private StackPane pane;
    private boolean selected;
    private String omschrijving;
    
    public Card(String omschrijving, String kleur) {
        StackPane innerpane = new StackPane();
        innerpane.setMinSize(0, 0);
        innerpane.setPrefSize(80, 100);
        innerpane.setMaxSize(80, 100);
        innerpane.setStyle("-fx-background-color: " + kleur + "; "
                + "-fx-border-color: black;");
        
        Label label = new Label(omschrijving);
        label.setStyle("-fx-background-color: black; "
                + "-fx-text-fill: white;");
        
        innerpane.getChildren().add(label);
        pane = new StackPane(innerpane);
        pane.setMinSize(0, 0);
        pane.setPrefSize(84, 104);
        pane.setMaxSize(84, 104);
        this.omschrijving = omschrijving;
    }
    
    public Pane getContent(){
        return pane;
    }
    
    public void selectPane(){
        if(selected){
            pane.setStyle("-fx-background-color: white");
            selected = false;
        }else{
            pane.setStyle("-fx-background-color: gray");
            selected = true;
        }
    }

    public String getOmschrijving(){
        return omschrijving;
    }
    
    public boolean getSelected(){
        return selected;
    }
}
